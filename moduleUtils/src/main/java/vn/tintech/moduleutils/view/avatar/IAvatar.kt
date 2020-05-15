package vn.tintech.moduleutils.view.avatar

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri

interface IAvatar {
    /**
     * [name] is used in conjunction with [email] to set initials.
     */
    var name: String
    var email: String
    var avatarImageBitmap: Bitmap?
    var avatarImageDrawable: Drawable?
    var avatarImageResourceId: Int?
    var avatarImageUri: Uri?
    var avatarBackgroundColor: Int?
}