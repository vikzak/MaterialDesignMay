package com.example.materialdesign.repository.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityRecyclerItemEarthBinding
import com.example.materialdesign.databinding.ActivityRecyclerItemHeaderBinding
import com.example.materialdesign.databinding.ActivityRecyclerItemMarsBinding

class RecyclerActivityAdapter(val onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<RecyclerActivityAdapter.BaseViewHolde >() {
    private lateinit var listData: MutableList<Data>

    fun setData(listData: MutableList<Data>) {
        this.listData = listData
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

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    fun appendItem() {
        listData.add(generateData())
        notifyItemInserted(listData.size-1)
    }

    fun generateData(): Data {
        return Data("Mars", type = TYPE_MARS)
    }

    inner class EarthViewHolder(view: View) : BaseViewHolde(view) {
        override fun bind(data: Data) {
            ActivityRecyclerItemEarthBinding.bind(itemView).apply {
                tvName.text = data.name
                tvDescription.text = data.description
                ivEarth.setOnClickListener {
                    onClickItemListener.onItemClick(data)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolde(view) {
        override fun bind(data: Data) {
            ActivityRecyclerItemMarsBinding.bind(itemView).apply {
                tvName.text = data.name
                ivMars.setOnClickListener {
                    onClickItemListener.onItemClick(data)
                }
                addItemIV.setOnClickListener {
                    listData.add(layoutPosition,generateData())
                    notifyItemInserted(layoutPosition)
                }
                removeItemIV.setOnClickListener {
                    listData.removeAt(layoutPosition)
                    notifyItemRemoved(layoutPosition)
                }
            }
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolde(view) {
        override fun bind(data: Data) {
            ActivityRecyclerItemHeaderBinding.bind(itemView).apply {
                tvHeader.text = data.name
                itemView.setOnClickListener {
                    onClickItemListener.onItemClick(data)
                }
            }
        }
    }


    abstract class BaseViewHolde(view: View):RecyclerView.ViewHolder(view){
        abstract fun bind(data: Data)
    }


}