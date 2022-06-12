package com.example.materialdesign.view.recycler_view


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityMainBinding
import com.example.materialdesign.databinding.ActivityRecyclerBinding
import com.example.materialdesign.repository.recycler.Data
import com.example.materialdesign.repository.recycler.OnClickItemListener
import com.example.materialdesign.repository.recycler.RecyclerActivityAdapter
import com.example.materialdesign.repository.recycler.TYPE_MARS

class RecyclerActivity : AppCompatActivity() {

    lateinit var binding:ActivityRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyOrangeTheme)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = arrayListOf(
            Data(getString(R.string.text_earht),getString(R.string.recycler_earth_description)),
            Data(getString(R.string.text_earht),getString(R.string.recycler_earth_description)),
            Data(getString(R.string.text_earht),getString(R.string.recycler_earth_description)),
            Data(getString(R.string.text_earht),getString(R.string.recycler_earth_description)),
            Data(getString(R.string.text_earht),getString(R.string.recycler_earth_description)),

            Data(getString(R.string.text_mars), type = TYPE_MARS),
            Data(getString(R.string.text_mars), type = TYPE_MARS),
            Data(getString(R.string.text_mars), type = TYPE_MARS),
            Data(getString(R.string.text_mars), type = TYPE_MARS),
            Data(getString(R.string.text_mars), type = TYPE_MARS)
        )
        data.shuffle()
        val adapter = RecyclerActivityAdapter(object : OnClickItemListener{
            override fun onItemClick(data: Data) {
                Toast.makeText(this@RecyclerActivity,"result: ${data.name}",Toast.LENGTH_SHORT).show()
            }
        })
        adapter.setData(data)
        binding.recyclerView.adapter = adapter
    }

}