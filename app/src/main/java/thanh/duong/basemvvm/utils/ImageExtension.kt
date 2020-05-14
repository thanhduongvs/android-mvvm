package thanh.duong.basemvvm.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import java.io.File
import java.util.concurrent.ExecutionException


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

fun ImageView.loadThumb(urlThumb: String){
    // We don't want Glide to crop or resize our image
    val options = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .override(Target.SIZE_ORIGINAL)
        .dontTransform()

    Glide.with(this)
        .load(urlThumb)
        .apply(options)
        .into(this)
}

fun ImageView.loadFull(urlFull: String, urlThumb: String){
    // We don't want Glide to crop or resize our image
    val options = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .override(Target.SIZE_ORIGINAL)
        .dontTransform()

    val thumbRequest: RequestBuilder<Drawable> = Glide.with(this)
        .load(urlThumb)
        .apply(options)

    Glide.with(this)
        .load(urlFull)
        .apply(options)
        .thumbnail(thumbRequest)
        .into(this)
}

fun ImageView.loadFromResource(drawableId: Int){
    Glide.with(this)
        .load(drawableId)
        .centerCrop()
        .into(this)
}

fun ImageView.loadFromLocal(path: String){
    Glide.with(this)
        .load(File(path)) // Uri of the picture
        .centerCrop()
        .into(this)
}

fun ImageView.resume() {
    Glide.with(this.context).resumeRequests()
}

fun ImageView.pause() {
    Glide.with(this.context).pauseRequests()
}

fun ImageView.downloadImage(url: String): String {
    val fileFutureTarget = Glide.with(this.context)
        .load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
    return try {
        fileFutureTarget.get().absolutePath
    } catch (e: InterruptedException) {
        e.printStackTrace()
        null.toString()
    } catch (e: ExecutionException) {
        e.printStackTrace()
        null.toString()
    }
}
