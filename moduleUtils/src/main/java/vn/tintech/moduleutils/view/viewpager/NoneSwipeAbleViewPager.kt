package vn.tintech.moduleutils.view.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager

class NoneSwipeAbleViewPager : ViewPager {

    private var mMinHeight: Int = 0
    private var mCurrentView: View? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        @Suppress("NAME_SHADOWING") var heightMeasureSpec = heightMeasureSpec
        if (mCurrentView == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        var height = 0
        mCurrentView?.measure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )
        val h = mCurrentView?.measuredHeight
        if (h != null) {
            if (h > height) height = h
        }
        if (height == 0) {
            height = this.mMinHeight
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun measureCurrentView(currentView: View?) {
        mCurrentView = currentView
        requestLayout()
    }
}