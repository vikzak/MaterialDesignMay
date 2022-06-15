package com.example.materialdesign.repository.recycler

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityRecyclerItemEarthBinding
import com.example.materialdesign.databinding.ActivityRecyclerItemHeaderBinding
import com.example.materialdesign.databinding.ActivityRecyclerItemMarsBinding
import com.example.materialdesign.view.recycler_view.ItemTouchHelperAdapter
import com.example.materialdesign.view.recycler_view.ItemTouchHelperViewHolde
import com.example.materialdesign.view.recycler_view.diffutils.Change
import com.example.materialdesign.view.recycler_view.diffutils.DiffUtilsCalback
import com.example.materialdesign.view.recycler_view.diffutils.createCombinePayloads

class RecyclerActivityAdapter(
    val onClickItemListener: OnClickItemListener,
    val onStartDragListener: OnStartDragListener
) :
    RecyclerView.Adapter<RecyclerActivityAdapter.BaseViewHolde>(), ItemTouchHelperAdapter {
    var listData: MutableList<Pair<Data, Boolean>> = mutableListOf()

    fun setData(newListData: MutableList<Pair<Data, Boolean>>) {
        val res = DiffUtil.calculateDiff(DiffUtilsCalback(this.listData, newListData))
        res.dispatchUpdatesTo(this)
        listData.clear()
        listData.addAll(newListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolde {
        return when (viewType) {
            TYPE_EARTH -> {
                val binding = ActivityRecyclerItemEarthBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                EarthViewHolder(binding.root)
            }
            TYPE_HEADER -> {
                val binding = ActivityRecyclerItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(binding.root)
            }
            else -> {
                val binding = ActivityRecyclerItemMarsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MarsViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolde, position: Int) {
        holder.bind(listData[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolde,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty() && holder is MarsViewHolder) {
            val newData =
                createCombinePayloads(payloads as List<Change<Pair<Data, Boolean>>>).newData
            val oldData =
                createCombinePayloads(payloads as List<Change<Pair<Data, Boolean>>>).oldData

            if (newData.first.name != oldData.first.name) {
                ActivityRecyclerItemMarsBinding.bind(holder.itemView).tvName.text =
                    newData.first.name
            }
//            if (newData.first.description!=oldData.first.description){
//                ActivityRecyclerItemMarsBinding.bind(holder.itemView).marsDescriptionTV.text = newData.first.description
//            }

        } else {
            super.onBindViewHolder(holder, position, payloads)
        }

    }


    override fun getItemCount(): Int {
        return listData.size
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].first.type
    }

    fun appendItem() {
        listData.add(generateData())
        notifyItemInserted(listData.size - 1)
    }

    fun generateData() = Pair(Data(name = "Mars", type = TYPE_MARS), false)


    inner class EarthViewHolder(view: View) : BaseViewHolde(view) {
        override fun bind(data: Pair<Data, Boolean>) {
            ActivityRecyclerItemEarthBinding.bind(itemView).apply {
                tvName.text = data.first.name
                tvDescription.text = data.first.description
                ivEarth.setOnClickListener {
                    onClickItemListener.onItemClick(data.first)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolde(view), ItemTouchHelperViewHolde {
        override fun bind(data: Pair<Data, Boolean>) {
            ActivityRecyclerItemMarsBinding.bind(itemView).apply {
                tvName.text = data.first.name
                ivMars.setOnClickListener {
                    onClickItemListener.onItemClick(data.first)
                }
                addItemIV.setOnClickListener {
                    listData.add(layoutPosition, generateData())
                    notifyItemInserted(layoutPosition)
                }
                removeItemIV.setOnClickListener {
                    listData.removeAt(layoutPosition)
                    notifyItemRemoved(layoutPosition)
                }
                moveItemUp.setOnClickListener {
                    if (layoutPosition > 1) {
                        listData.removeAt(layoutPosition).apply {
                            listData.add(layoutPosition - 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition - 1)
                    }
                }
                moveItemDown.setOnClickListener {
                    if ((layoutPosition + 1) < listData.size) {
                        listData.removeAt(layoutPosition).apply {
                            listData.add(layoutPosition + 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition + 1)
                    }
                }
                marsDescriptionTV.visibility =
                    if (listData[layoutPosition].second) View.VISIBLE else View.GONE
                itemView.setOnClickListener {
                    listData[layoutPosition] = listData[layoutPosition].let {
                        it.first to !it.second
                    }
                    notifyItemChanged(layoutPosition)
                }
                dragHandleIV.setOnTouchListener { view, motionEvent ->
                    if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                        onStartDragListener.onStartDrag(this@MarsViewHolder)
                    }
                    return@setOnTouchListener false
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }


    override fun onItemMove(firstPosition: Int, toPosition: Int) {
        listData.removeAt(firstPosition).apply {
            listData.add(toPosition, this)
        }
        notifyItemMoved(firstPosition, toPosition)
    }

    override fun onItemDismiss(dismissPosition: Int) {
        listData.removeAt(dismissPosition)
        notifyItemRemoved(dismissPosition)
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolde(view) {
        override fun bind(data: Pair<Data, Boolean>) {
            ActivityRecyclerItemHeaderBinding.bind(itemView).apply {
                tvHeader.text = data.first.name
                itemView.setOnClickListener {
                    onClickItemListener.onItemClick(data.first)
                }
            }
        }
    }


    abstract class BaseViewHolde(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Pair<Data, Boolean>)
    }


}