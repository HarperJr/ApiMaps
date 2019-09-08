package com.vlsu.maps.data.api.net

import com.vlsu.maps.domain.rx.AppSchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.Disposables
import java.io.ByteArrayOutputStream
import java.util.concurrent.atomic.AtomicBoolean

class TcpTunnel(private val host: String, private val port: Int) {
    private val tcpConnectionListeners = mutableMapOf<String, TcpConnectionListener>()
    private val isConnected = AtomicBoolean()

    private var connectionStateDisposable = Disposables.disposed()
    private var connectionDisposable = Disposables.disposed()
    private var connection: TcpConnection? = null

    fun send(bytes: ByteArray) {
        if (isConnected()) {
            connection!!.write(bytes)
        }
    }

    fun subscribe(
        key: String,
        onConnected: () -> Unit,
        onNext: (ByteArray) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (tcpConnectionListeners.isEmpty()) {
            connectionStateDisposable.dispose()
            connectionStateDisposable = connect()
        }
        tcpConnectionListeners[key] = TcpConnectionListener(onConnected, onNext, onError)
    }

    fun unsubscribe(key: String) {
        tcpConnectionListeners.remove(key)
        if (tcpConnectionListeners.isEmpty()) {
            connectionStateDisposable.dispose()
            connectionStateDisposable = disconnect()
        }
    }

    private fun connect() = Completable
        .fromAction {
            if (isConnected.compareAndSet(false, true)) {
                connection = TcpConnection(host, port)
                connection!!.connect()
            }
        }
        .subscribeOn(AppSchedulerProvider.io())
        .doOnComplete { tcpConnectionListeners.values.forEach { it.onConnected.invoke() } }
        .doOnError {
            connectionStateDisposable.dispose()
            connectionStateDisposable = disconnect()
        }
        .andThen(connectionObservable)
        .subscribe({ bytes ->
            tcpConnectionListeners.values.forEach { it.onNext.invoke(bytes) }
        }, { throwable ->
            tcpConnectionListeners.values.forEach { it.onError.invoke(throwable) }
        })

    private fun disconnect() = Completable
        .fromAction {
            if (isConnected.compareAndSet(true, false)) {
                connection!!.disconnect()
                connection = null
            }
        }
        .subscribeOn(AppSchedulerProvider.io())
        .subscribe { connectionDisposable.dispose() }

    private fun isConnected() = isConnected.get()

    private val connectionObservable = Observable
        .create<ByteArray> { subscriber ->
            try {
                val outputByteArrayStream = ByteArrayOutputStream()
                while (isConnected()) {
                    while (connection!!.available() > 0) {
                        val byteBuffer = ByteArray(connection!!.available()) //todo should be declared out of this loop
                        (connection?.read(byteBuffer, byteBuffer.size)) != -1
                        outputByteArrayStream.write(byteBuffer)
                        subscriber.onNext(outputByteArrayStream.toByteArray())
                        outputByteArrayStream.flush()
                    }
                }
                outputByteArrayStream.close()
            } catch (ex: Exception) {
                subscriber.onError(ex)
            }
        }
        .observeOn(AppSchedulerProvider.io())
}

data class TcpConnectionListener(
    val onConnected: () -> Unit,
    val onNext: (ByteArray) -> Unit,
    val onError: (Throwable) -> Unit
)

