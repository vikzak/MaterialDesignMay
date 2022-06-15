package com.example.materialdesign.view.recycler_view


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityRecyclerBinding
import com.example.materialdesign.repository.recycler.*

class RecyclerActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecyclerBinding
    lateinit var itemTouchHelper: ItemTouchHelper
    lateinit var adapter: RecyclerActivityAdapter
    private var isNewList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyOrangeTheme)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val lat = 20
        //val lon = 23
        //val myCoordonates = lat to lon

        val listData = arrayListOf(
            Pair(Data(id = 1, name = getString(R.string.text_earht), description = getString(R.string.recycler_earth_description)), false),
            Pair(Data(id = 2, name = getString(R.string.text_mars), type = TYPE_MARS), false),
            Pair(Data(id = 3, name = getString(R.string.text_mars), type = TYPE_MARS), false),
            Pair(Data(id = 4, name = getString(R.string.text_earht),description = getString(R.string.recycler_earth_description)), false),
            Pair(Data(id = 5, name = getString(R.string.text_earht),description = getString(R.string.recycler_earth_description)), false),
        )
        listData.shuffle()
        listData.add(0, Pair(Data(id = 0, name = getString(R.string.text_header), type = TYPE_HEADER), false))
        adapter = RecyclerActivityAdapter(object : OnClickItemListener {
            override fun onItemClick(data: Data) {
                Toast.makeText(this@RecyclerActivity, "result: ${data.name}", Toast.LENGTH_SHORT)
                    .show()
            }
        }, object : OnStartDragListener {
            override fun onStartDrag(view: RecyclerView.ViewHolder) {
                itemTouchHelper.startDrag(view)
            }
        })

        adapter.setData(listData)
        binding.recyclerView.adapter = adapter

        binding.recyclerActivityFAB.setOnClickListener {
            adapter.appendItem()
           binding.recyclerView.smoothScrollToPosition(adapter.itemCount)///----
        }

        binding.recyclerActivityDiffUtilsFAB.setOnClickListener {
            isNewList=!isNewList
            adapter.setData(createItemList(isNewList))
        }

        itemTouchHelper =
            ItemTouchHelper(ItemTouchHelperCallBack(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        //ItemTouchHelper(ItemTouchHelperCallBack(adapter)).attachToRecyclerView(binding.recyclerView)
    }

    private fun createItemList(instanceNumber: Boolean):MutableList<Pair<Data,Boolean>>{
        return when(instanceNumber){
            false -> arrayListOf(
                Pair(Data(0,"Header"),false),
                Pair(Data(1,"Mars",""),false),
                Pair(Data(2,"Mars",""),false),
                Pair(Data(3,"Mars",""),false),
                Pair(Data(4,"Mars",""),false),
                Pair(Data(5,"Mars",""),false),
                Pair(Data(6,"Mars",""),false)
            )
            true -> arrayListOf(
                Pair(Data(0,"Header"),false),
                Pair(Data(1,"Mars",""),false),
                Pair(Data(1,"Yupiter",""),false),
                Pair(Data(1,"Satyrn",""),false),
                Pair(Data(1,"Neptune",""),false),
                Pair(Data(1,"Mars",""),false),
                Pair(Data(1,"Earth",""),false)
            )
        }
    }

    class ItemTouchHelperCallBack(val recyclerActivityAdapter: RecyclerActivityAdapter) :
        ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            from: RecyclerView.ViewHolder,
            to: RecyclerView.ViewHolder
        ): Boolean {
            if (to.adapterPosition > 0) recyclerActivityAdapter.onItemMove(from.adapterPosition, to.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            recyclerActivityAdapter.onItemDismiss(viewHolder.adapterPosition)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            if (viewHolder is RecyclerActivityAdapter.MarsViewHolder)
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
                    (viewHolder as RecyclerActivityAdapter.MarsViewHolder).onItemSelected()
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            if (viewHolder is RecyclerActivityAdapter.MarsViewHolder)
                (viewHolder as RecyclerActivityAdapter.MarsViewHolder).onItemClear()
        }

    }

}