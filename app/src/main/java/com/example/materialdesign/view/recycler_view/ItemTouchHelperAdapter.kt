package com.example.materialdesign.view.recycler_view

interface ItemTouchHelperAdapter {
    fun onItemMove(firstPosition: Int, toPosition: Int)
    fun onItemDismiss(dismissPosition: Int)
}

interface ItemTouchHelperViewHolde {
    fun onItemSelected()
    fun onItemClear()
}