package com.vlsu.maps.extensions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun View.visibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun <T : Fragment> T.withArgs(receiver: Bundle.() -> Unit): T {
    arguments = Bundle().apply(receiver)
    return this
}

inline fun <reified T> Fragment.args(key: String? = null, defaultValue: T? = null): ReadWriteProperty<Fragment, T> {
    return BundleExtractorDelegate { thisRef, property ->
        val bundleKey = key ?: property.name
        thisRef.arguments.extract(bundleKey, defaultValue)
    }
}

inline fun <reified T> Bundle?.extract(key: String? = null, defaultValue: T? = null): T {
    val result = this?.get(key) ?: defaultValue
    return result as? T ?: throw ClassCastException("Property for $key has different class type")
}


class BundleExtractorDelegate<in R, T>(private val initializer: (R, KProperty<*>) -> T) : ReadWriteProperty<R, T> {

    private object EMPTY

    private var value: Any? = EMPTY

    override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        this.value = value
    }

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        if (value == EMPTY) {
            value = initializer(thisRef, property)
        }

        @Suppress("UNCHECKED_CAST")
        return value as T
    }
}