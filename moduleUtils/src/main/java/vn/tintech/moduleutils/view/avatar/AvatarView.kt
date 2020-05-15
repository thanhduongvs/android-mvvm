package vn.tintech.moduleutils.view.avatar

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import vn.tintech.moduleutils.R

enum class AvatarStyle {
    CIRCLE, SQUARE, SQUIRCLE
}

/**
 * [AvatarView] is a custom ImageView that displays the initials of a person on top of a colored circular
 * background. The initials are extracted from their name or email. The color of the circular
 * background is computed from the name and is based on an array of colors.
 */
open class AvatarView : AppCompatImageView {
    companion object {
        internal val DEFAULT_AVATAR_SIZE = AvatarSize.LARGE
        internal val DEFAULT_AVATAR_STYLE = AvatarStyle.CIRCLE
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.AvatarView)
        val avatarSizeOrdinal = styledAttrs.getInt(R.styleable.AvatarView_avatarSize, DEFAULT_AVATAR_SIZE.ordinal)
        val avatarStyleOrdinal = styledAttrs.getInt(R.styleable.AvatarView_avatarStyle, DEFAULT_AVATAR_STYLE.ordinal)

        name = styledAttrs.getString(R.styleable.AvatarView_name) ?: ""
        email = styledAttrs.getString(R.styleable.AvatarView_email) ?: ""
        avatarSize = AvatarSize.values()[avatarSizeOrdinal]
        avatarStyle = AvatarStyle.values()[avatarStyleOrdinal]

        val avatarImageResourceId = styledAttrs.getResourceId(R.styleable.AvatarView_avatarImageDrawable, 0)
        if (avatarImageResourceId > 0 && resources.getResourceTypeName(avatarImageResourceId) == "drawable")
            avatarImageDrawable = styledAttrs.getDrawable(R.styleable.AvatarView_avatarImageDrawable)

        val avatarBackgroundColorResourceId = styledAttrs.getResourceId(R.styleable.AvatarView_avatarBackgroundColor, 0)
        if (avatarBackgroundColorResourceId > 0 && resources.getResourceTypeName(avatarBackgroundColorResourceId) == "color")
            avatarBackgroundColor = ContextCompat.getColor(context, avatarBackgroundColorResourceId)

        styledAttrs.recycle()
    }

    var name: String = ""
        set(value) {
            field = value
            initials.setInfo(name, email, avatarBackgroundColor)
        }
    var email: String = ""
        set(value) {
            field = value
            initials.setInfo(name, email, avatarBackgroundColor)
        }
    var avatarImageBitmap: Bitmap? = null
        set(value) {
            field = value
            setImageBitmap(value)
        }
    var avatarImageDrawable: Drawable? = null
        set(value) {
            field = value
            setImageDrawable(value)
        }
    var avatarImageResourceId: Int? = null
        set(value) {
            field = value
            value?.let { setImageResource(it) }
        }
    var avatarImageUri: Uri? = null
        set(value) {
            field = value
            setImageURI(value)
        }
    @ColorInt
    var avatarBackgroundColor: Int? = null
        set(value) {
            field = value
            initials.setInfo(name, email, avatarBackgroundColor)
        }
    /**
     * Defines the [AvatarSize] applied to the avatar's height and width.
     */
    var avatarSize: AvatarSize = DEFAULT_AVATAR_SIZE
        set(value) {
            if (field == value)
                return

            field = value
            requestLayout()
        }
    /**
     * Defines the [AvatarStyle] applied to the avatar.
     */
    var avatarStyle: AvatarStyle = DEFAULT_AVATAR_STYLE
        set(value) {
            if (field == value)
                return

            field = value
            invalidate()
        }

    private val initials = InitialsDrawable(context)
    private val avatarDisplaySize: Int
        get() = avatarSize.getDisplayValue(context)
    private var path: Path = Path()

    override fun draw(canvas: Canvas) {
        val avatarBoundsRect = Rect(0, 0, avatarDisplaySize, avatarDisplaySize)

        initials.avatarStyle = avatarStyle
        initials.bounds = avatarBoundsRect
        initials.draw(canvas)

        path.reset()
        when (avatarStyle) {
            AvatarStyle.CIRCLE ->
                path.addCircle(
                    avatarBoundsRect.width() / 2f,
                    avatarBoundsRect.height() / 2f,
                    avatarBoundsRect.width() / 2f,
                    Path.Direction.CW
                )
            AvatarStyle.SQUARE -> {
                val cornerRadius = resources.getDimension(R.dimen.avatar_square_corner_radius)
                path.addRoundRect(RectF(avatarBoundsRect), cornerRadius, cornerRadius, Path.Direction.CW)
            }
            AvatarStyle.SQUIRCLE -> {
                path = getSquirclePaath((avatarBoundsRect.width()/2f).toInt(), (avatarBoundsRect.width()/2f).toInt(), (avatarBoundsRect.width()/2f).toInt())!!
            }
        }
        canvas.clipPath(path)

        super.draw(canvas)
    }

    override fun setImageDrawable(drawable: Drawable?) {
        if (drawable is BitmapDrawable)
            setImageBitmap(drawable.bitmap)
        else
            super.setImageDrawable(drawable)
    }

    override fun setImageBitmap(bitmap: Bitmap?) {
        if (bitmap == null)
            return

        super.setImageDrawable(BitmapDrawable(resources, bitmap))
    }

    override fun setImageResource(resId: Int) {
        val bitmap = BitmapFactory.decodeResource(resources, resId)
        setImageBitmap(bitmap)
    }

    override fun setImageURI(uri: Uri?) {
        if (uri == null)
            return

        try {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            setImageBitmap(bitmap)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            resolveSizeAndState(avatarDisplaySize, widthMeasureSpec, 0),
            resolveSizeAndState(avatarDisplaySize, heightMeasureSpec, 0)
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        layoutParams.width = avatarDisplaySize
        layoutParams.height = avatarDisplaySize

        super.onSizeChanged(w, h, oldw, oldh)
    }

    private fun getSquirclePaath(left: Int, top: Int, radius: Int): Path? { //Formula: (|x|)^3 + (|y|)^3 = radius^3
        val radiusToPow = radius * radius * radius.toDouble()
        val path = Path()
        path.moveTo((-radius).toFloat(), 0f)
        for (x in -radius..radius) path.lineTo(
            x.toFloat(),
            Math.cbrt(radiusToPow - Math.abs(x * x * x)).toFloat()
        )
        for (x in radius downTo -radius) path.lineTo(
            x.toFloat(),
            (-Math.cbrt(radiusToPow - Math.abs(x * x * x))).toFloat()
        )
        path.close()
        val matrix = Matrix()
        matrix.postTranslate((left + radius).toFloat(), (top + radius).toFloat())
        path.transform(matrix)
        return path
    }
}

fun AvatarView.setAvatar(avatar: IAvatar) {
    name = avatar.name
    email = avatar.email
    avatarImageBitmap = avatar.avatarImageBitmap
    avatarImageDrawable = avatar.avatarImageDrawable
    avatarImageResourceId = avatar.avatarImageResourceId
    avatarImageUri = avatar.avatarImageUri
    avatarBackgroundColor = avatar.avatarBackgroundColor
}