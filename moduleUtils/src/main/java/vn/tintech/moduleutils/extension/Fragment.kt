package vn.tintech.moduleutils.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import vn.tintech.moduleutils.R

val HORIZONTAL_ANIMATIONS = intArrayOf(R.anim.horizontal_enter, R.anim.horizontal_exit, R.anim.horizontal_pop_enter, R.anim.horizontal_pop_exit)

val VERTICAL_ANIMATIONS = intArrayOf(R.anim.horizontal_enter, R.anim.horizontal_exit, R.anim.horizontal_pop_enter, R.anim.horizontal_pop_exit)


/**
 * [Fragment].[FragmentManager]
 */
fun Fragment.addFragment(fragment: Fragment, @IdRes container: Int, backStack: Boolean = true, animations: IntArray? = VERTICAL_ANIMATIONS) {

    val tag = fragment::class.java.simpleName
    childFragmentManager.scheduleTransaction({
        add(container, fragment, tag)
        if (backStack) addToBackStack(tag)
    }, animations)
}

fun Fragment.replaceFragment(fragment: Fragment, @IdRes container: Int, backStack: Boolean = true, animations: IntArray? = VERTICAL_ANIMATIONS) {

    val tag = fragment::class.java.simpleName
    childFragmentManager.scheduleTransaction({
        replace(container, fragment, tag)
        if (backStack) addToBackStack(tag)
    }, animations)
}

fun Fragment.isNotExist(cls: Class<*>): Boolean {
    val tag = cls.simpleName
    return null == childFragmentManager.findFragmentByTag(tag)
}

fun Fragment.remove(cls: Class<Fragment>, animations: IntArray? = VERTICAL_ANIMATIONS) {
    remove(cls.simpleName, animations)
}

fun Fragment.remove(tag: String?, animations: IntArray? = null) {

    tag ?: return
    val fragment = childFragmentManager.findFragmentByTag(tag) ?: return
    childFragmentManager.scheduleTransaction({
        remove(fragment)
    }, animations)
}


/**
 * [FragmentActivity].[FragmentManager]
 */
fun FragmentActivity.addFragment(fragment: Fragment, @IdRes container: Int, backStack: Boolean = true, animations: IntArray? = VERTICAL_ANIMATIONS) {

    val tag = fragment::class.java.simpleName
    supportFragmentManager.scheduleTransaction({
        add(container, fragment, tag)
        if (backStack) addToBackStack(tag)
    }, animations)
}

fun FragmentActivity.replaceFragment(fragment: Fragment, @IdRes container: Int, backStack: Boolean = true, animations: IntArray? = VERTICAL_ANIMATIONS) {

    val tag = fragment::class.java.simpleName
    supportFragmentManager.scheduleTransaction({
        replace(container, fragment, tag)
        if (backStack) addToBackStack(tag)
    }, animations)
}

fun FragmentActivity.isNotExist(cls: Class<*>): Boolean {
    val tag = cls.simpleName
    return null == supportFragmentManager.findFragmentByTag(tag)
}

fun FragmentActivity.remove(cls: Class<*>, animations: IntArray? = VERTICAL_ANIMATIONS) {
    remove(cls.simpleName, animations)
}

fun FragmentActivity.remove(tag: String?, animations: IntArray? = VERTICAL_ANIMATIONS) {
    tag ?: return
    val fragment = supportFragmentManager.findFragmentByTag(tag) ?: return
    supportFragmentManager.scheduleTransaction({
        remove(fragment)
    }, animations)
}

fun FragmentActivity.clearStack() {
    val sfm = this.supportFragmentManager
    for (fragment in sfm.fragments) {
        if (fragment !is Fragment) continue
        sfm.beginTransaction().remove(fragment).commit()
    }
    sfm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

fun FragmentManager.scheduleTransaction(block: FragmentTransaction.() -> Unit, animations: IntArray? = VERTICAL_ANIMATIONS) {

    val transaction = beginTransaction()
    if (null != animations) transaction.setCustomAnimations(animations[0], animations[1], animations[2], animations[3])
    transaction.block()
    transaction.commitAllowingStateLoss()

}