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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyOrangeTheme)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lat = 20
        val lon = 23
        val myCoordonates = lat to lon

        val listData = arrayListOf(
            Pair(
                Data(
                    getString(R.string.text_earht),
                    getString(R.string.recycler_earth_description)
                ), false
            ),
            Pair(Data(getString(R.string.text_mars), type = TYPE_MARS), false),
            Pair(Data(getString(R.string.text_mars), type = TYPE_MARS), false),
            Pair(
                Data(
                    getString(R.string.text_earht),
                    getString(R.string.recycler_earth_description)
                ), false
            ),
            Pair(
                Data(
                    getString(R.string.text_earht),
                    getString(R.string.recycler_earth_description)
                ), false
            ),
        )
        listData.shuffle()
        listData.add(0, Pair(Data(getString(R.string.text_header), type = TYPE_HEADER), false))
        val adapter = RecyclerActivityAdapter(object : OnClickItemListener {
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
            binding.recyclerView.smoothScrollToPosition(adapter.itemCount)
        }
        itemTouchHelper =
            ItemTouchHelper(ItemTouchHelperCallBack(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        //ItemTouchHelper(ItemTouchHelperCallBack(adapter)).attachToRecyclerView(binding.recyclerView)
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