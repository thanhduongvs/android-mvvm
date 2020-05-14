package vn.tintech.moduleutils.extension

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.io.Serializable

/**
 * https://github.com/boluok/WatchGuard/blob/4c2f00b2c4a79191bc8d06c51981768b1d4ead09/app/src/main/java/com/qucoon/watchguard/utils/Arguments.kt
 */
fun Fragment.withArguments(vararg arguments: Pair<String, Serializable>): Fragment {
    val bundle = Bundle()
    arguments.forEach { bundle.putSerializable(it.first, it.second) }
    this.arguments = bundle
    return this
}

/**
 * Retrieve property from intent
 */
fun <T : Any> FragmentActivity.argument(key: String) = lazy { intent.extras?.get(key) as T }

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> FragmentActivity.argument(key: String, defaultValue: T? = null) = lazy {
    intent.extras?.get(key) as? T ?: defaultValue
}

/**
 * Retrieve property from intent
 */
fun <T : Any> Fragment.argument(key: String) = lazy { arguments?.get(key) as T }

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> Fragment.argument(key: String, defaultValue: T? = null) = lazy {
    arguments?.get(key)  as? T ?: defaultValue
}