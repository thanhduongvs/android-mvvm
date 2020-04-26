package thanh.duong.basemvvm.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}

fun ImageView.loadImageSsl(imageUrl: String) {
    GlideApp.with(this)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}
