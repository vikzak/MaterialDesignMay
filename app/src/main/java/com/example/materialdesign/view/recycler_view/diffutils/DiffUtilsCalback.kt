package com.example.materialdesign.view.recycler_view.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.materialdesign.repository.recycler.Data

class DiffUtilsCalback(
    private val oldItems: List<Pair<Data, Boolean>>,
    private val newItems: List<Pair<Data, Boolean>>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].first.id == newItems[newItemPosition].first.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].first.name == newItems[newItemPosition].first.name
    }


    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return Change(oldItem, newItem)
    }
}