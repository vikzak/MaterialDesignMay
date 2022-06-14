package com.example.materialdesign.view.recycler_view


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityRecyclerBinding
import com.example.materialdesign.repository.recycler.*

class RecyclerActivity : AppCompatActivity() {

    lateinit var binding:ActivityRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyOrangeTheme)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listData = arrayListOf(
            Data(getString(R.string.text_earht),getString(R.string.recycler_earth_description)),
            Data(getString(R.string.text_mars), type = TYPE_MARS)
        )
        listData.shuffle()
        listData.add(0,Data(getString(R.string.text_header), type = TYPE_HEADER))
        val adapter = RecyclerActivityAdapter(object : OnClickItemListener{
            override fun onItemClick(data: Data) {
                Toast.makeText(this@RecyclerActivity,"result: ${data.name}",Toast.LENGTH_SHORT).show()
            }
        })
        adapter.setData(listData)
        binding.recyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener {
            adapter.appendItem()
            binding.recyclerView.smoothScrollToPosition(adapter.itemCount)
        }
    }

}