package com.example.materialdesign.view.recycler_view.diffutils

data class Change<out T>(
    val oldData:T,
    val newData:T
)

fun <T> createCombinePayloads(payloads:List<Change<out T>>):Change<T> {
    val first = payloads.first()
    val last = payloads.last()
    return Change(first.oldData,last.newData)
}
