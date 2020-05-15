package vn.tintech.moduleutils.view.avatar

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.*
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import vn.tintech.moduleutils.R
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import java.util.*


/**
 * [InitialsDrawable] generates initials and background color for the AvatarView.
 */
internal class InitialsDrawable : Drawable {
    companion object {
        // This is the displayed value for avatars / headers when the name starts with a symbol
        private const val DEFAULT_SYMBOL_HEADER = '#'
        private const val DEFAULT_INITIALS_TEXT_SIZE_RATIO = 0.4f
        private var backgroundColors: IntArray? = null

        fun getInitials(name: String, email: String): String {
            val names = name.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            var initials = ""

            if (names.isNotEmpty())
                for (partialName in names) {
                    val trimmed = partialName.trim { it <= ' ' }
                    if (trimmed.isNotEmpty() && initials.length < 2) {
                        val initial = trimmed[0]
                        // Skip this character if it's in our ignored list
                        if (!Character.isLetterOrDigit(initial))
                            continue

                        initials += initial
                    }
                }

            if (initials.isEmpty())
                initials = if (email.length > 1) email.substring(0, 1) else DEFAULT_SYMBOL_HEADER.toString()

            return initials.toUpperCase(Locale.getDefault())
        }

        @ColorInt
        fun getInitialsBackgroundColor(backgroundColors: IntArray?, name: String, email: String): Int {
            val s = name + email
            val whichColor = Math.abs(s.hashCode()) % (backgroundColors?.size ?: 1)
            return backgroundColors?.get(whichColor) ?: 0
        }
    }

    var avatarStyle: AvatarStyle = AvatarStyle.SQUARE

    private val context: Context
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var path: Path = Path()
    private val textPaint: TextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private var initials: String? = null
    private var initialsBackgroundColor: Int = 0
    private var initialsLayout: Layout? = null

    constructor(context: Context) : super() {
        this.context = context

        textPaint.color = Color.WHITE
        textPaint.density = context.resources.displayMetrics.density

        if (backgroundColors == null)
            backgroundColors = getColors(context, R.array.avatar_background_colors)
    }

    override fun draw(canvas: Canvas) {
        val width = bounds.width()
        val height = bounds.height()

        path.reset()

        when (avatarStyle) {
            AvatarStyle.CIRCLE ->
                path.addCircle(width / 2f, height / 2f, width / 2f, Path.Direction.CW)
            AvatarStyle.SQUARE -> {
                val cornerRadius = context.resources.getDimension(R.dimen.avatar_square_corner_radius)
                path.addRoundRect(RectF(bounds), cornerRadius, cornerRadius, Path.Direction.CW)
            }
            AvatarStyle.SQUIRCLE -> {
                //path = getSquirclePaath(width, width, (width/2f).toInt())!!
                addSuperEllipseToPath(path, width/2f, height/2f, 0.6)
            }
        }

        paint.color = initialsBackgroundColor
        paint.style = Paint.Style.FILL

        canvas.drawPath(path, paint)

        initialsLayout?.let {
            canvas.save()
            canvas.translate(0f, (height - it.height) / 2f)
            it.draw(canvas)
            canvas.restore()
        }
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)

        if (TextUtils.isEmpty(initials))
            return

        val size = right - left
        textPaint.textSize = size * DEFAULT_INITIALS_TEXT_SIZE_RATIO

        val boringMetrics = BoringLayout.isBoring(initials, textPaint)

        if (boringMetrics != null)
            if (initialsLayout is BoringLayout)
                initialsLayout = (initialsLayout as BoringLayout).replaceOrMake(initials, textPaint, size,
                    Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, boringMetrics, false)
            else
                initialsLayout = BoringLayout.make(initials, textPaint, size,
                    Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, boringMetrics, false)
        else
            initialsLayout = StaticLayout(initials, textPaint, size,
                Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false)
    }

    override fun setAlpha(@IntRange(from = 0, to = 255) alpha: Int) {
        textPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) { }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    /**
     * Uses [name] and [email] to generate initials
     */
    fun setInfo(name: String, email: String, @ColorInt customBackgroundColor: Int? = null) {
        val initialsBackgroundColor = customBackgroundColor ?: getInitialsBackgroundColor(backgroundColors, name, email)
        initials = getInitials(name, email)
        this.initialsBackgroundColor = initialsBackgroundColor
        invalidateSelf()
    }

    fun getColors(context: Context, @ArrayRes arrayId: Int): IntArray {
        val array = context.resources.obtainTypedArray(arrayId)
        try {
            val results = IntArray(array.length())
            var i = 0
            val size = results.size
            while (i < size) {
                results[i] = array.getColor(i, 0)
                ++i
            }
            return results
        } finally {
            array.recycle()
        }
    }

    private fun addSuperEllipseToPath(p: Path, radX: Float, radY: Float, corners: Double) {
        var l = 0.0
        var angle: Double
        for (i in 0 until 360) {
            angle = Math.toRadians(l)
            val x = radX + getX(radX, angle, corners)
            val y = radY - getY(radY, angle, corners)
            if (i == 0) {
                p.moveTo(x, y)
            }
            l++
            angle = Math.toRadians(l)
            val x2 = radX + getX(radX, angle, corners)
            val y2 = radY - getY(radY, angle, corners)
            p.lineTo(x2, y2)
        }
        p.close()
    }

    private fun getX(radX: Float, angle: Double, corners: Double) =
        (abs(cos(angle)).pow(corners) * radX * sgn(
            cos(angle)
        )).toFloat()

    private fun getY(radY: Float, angle: Double, corners: Double) =
        (abs(sin(angle)).pow(corners) * radY * sgn(
            sin(angle)
        )).toFloat()

    private fun sgn(value: Double) = if (value > 0.0) 1.0 else if (value < 0.0) -1.0 else 0.0
}