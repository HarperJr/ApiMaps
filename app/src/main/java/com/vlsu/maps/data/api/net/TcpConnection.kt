package com.vlsu.maps.data.api.net

import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.Socket
import java.net.SocketException
import java.net.SocketTimeoutException

class TcpConnection(host: String, port: Int) {
    private val socket by lazy {
        try {
            Socket(host, port)
        } catch (ex: SocketException) {
            throw Exception("Unable to open socket connection on host $host, port $port")
        } catch (ex: SocketTimeoutException) {
            throw Exception("Unable to open socket cause host is unavailable, connection closed due to timeout on host $host, port $port")
        }
    }

    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    @Throws(Exception::class)
    fun connect() {
        inputStream = socket.getInputStream()

        outputStream = socket.getOutputStream()
    }

    fun write(bytes: ByteArray) = outputStream.write(bytes)

    fun read(bytes: ByteArray, size: Int) = inputStream.read(bytes, 0, size)

    fun disconnect() = socket.close()

    fun available(): Int = inputStream.available()
}