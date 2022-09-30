package com.vn.onekiwi.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle

inline fun <reified T : Any> Activity.startActivity (
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {})
{
    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
    {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivityForResult(intent, requestCode)
    }
}

inline fun <reified T : Any> Context.startActivity (
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {})
{
    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
    {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

/**
 * Example
 */

/** Simple Activities */
//startActivity<UserDetailActivity>()

/** Add Intent extras */
/*
startActivity<UserDetailActivity> {
    putExtra(INTENT_USER_ID, user.id)
}
*/

/** Add custom flags */
/*
startActivity<UserDetailActivity> {
    putExtra(INTENT_USER_ID, user.id)
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
}
*/

/** Add Shared Transistions */
/*
val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, avatar, "avatar")
startActivity<UserDetailActivity>(options = options) {
    putExtra(INTENT_USER_ID, user.id)
}
*/

/** Add requestCode for startActivityForResult() call */
/*
startActivity<UserDetailActivity>(requestCode = 1234) {
    putExtra(INTENT_USER_ID, user.id)
}
*/