package com.vn.onekiwi.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(parentView: ViewGroup, @LayoutRes resourceId: Int) :
    RecyclerView.ViewHolder(parentView.inflate(resourceId, false))