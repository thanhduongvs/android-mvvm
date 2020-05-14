package vn.tintech.moduleutils.extension

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.*
import vn.tintech.moduleutils.view.flowlayoutmanager.FlowLayoutManager

interface OnItemClickListener {
    fun onItemClicked(view: View, position: Int)
}

fun RecyclerView.initializeWithDefaults() = also {
    setHasFixedSize(true)
    isNestedScrollingEnabled = false
}

fun RecyclerView.linearLayoutManager() = also {
    layoutManager = LinearLayoutManager(this.context)
}

fun RecyclerView.horizontalLayoutManager() = also {
    layoutManager = LinearLayoutManager(
        this.context, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.verticalLayoutManager() = also {
    layoutManager = LinearLayoutManager(
        this.context, LinearLayoutManager.VERTICAL, false)
}

fun RecyclerView.gridLayoutManager(spanCount: Int = 2) = also {
    layoutManager = GridLayoutManager(this.context, spanCount)
}

fun RecyclerView.horizontalStaggeredGridLayoutManager(spanCount: Int) = also {
    layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL)
}

fun RecyclerView.verticalStaggeredGridLayoutManager(spanCount: Int) = also {
    layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
}

fun RecyclerView.flowLayoutManager(spanCount: Int = 2) = also {
    layoutManager =
        FlowLayoutManager(
            FlowLayoutManager.VERTICAL,
            Gravity.CENTER
        )
}

fun RecyclerView.addItemDecorationHorizontal() = also {
    addItemDecoration(
        DividerItemDecoration(this.context, DividerItemDecoration.HORIZONTAL)
    )
}

fun RecyclerView.addItemDecorationVertical() = also {
    addItemDecoration(
        DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
    )
}

fun RecyclerView.onItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener{
        override fun onChildViewDetachedFromWindow(view: View) {
            view.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            val holder = getChildViewHolder(view)
            view.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(view, holder.adapterPosition)
            }
        }
    })
}

/* Usage:
recyclerView.onItemClickListener(object: OnItemClickListener {
    override fun onItemClicked(view: View, position: Int) {
        // Your logic
    }
})
*/

fun RecyclerView.setAdapter(adapters: RecyclerView.Adapter<RecyclerView.ViewHolder>) = also {
    adapter = adapters
}
fun RecyclerView.checkAdapter(): Boolean = adapter != null