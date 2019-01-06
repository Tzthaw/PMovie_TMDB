package com.mmgoogleexpert.ptut.shared.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.mmgoogleexpert.ptut.listitemcodelab.viewholders.BaseViewHolder


abstract class BaseAdapter<VH: BaseViewHolder<T>, T: Any> (
        diffCallback: DiffUtil.ItemCallback<T>) : ListAdapter<T, VH>(diffCallback){
    override fun onBindViewHolder(holder: VH, position: Int) {
        if (holder.adapterPosition != RecyclerView.NO_POSITION){
            holder.bindData(getItem(holder.adapterPosition)!!)
        }
    }

}
