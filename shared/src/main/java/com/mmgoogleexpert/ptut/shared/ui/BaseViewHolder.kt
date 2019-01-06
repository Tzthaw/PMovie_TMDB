package com.mmgoogleexpert.ptut.shared.ui

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View

@Suppress("LeakingThis")
abstract class BaseViewHolder<T : Any>(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
    private lateinit var current: T

    @CallSuper
    open fun bindData(item: T) {
        current = item
    }

    init {
        itemView.setOnClickListener(this)
    }
}