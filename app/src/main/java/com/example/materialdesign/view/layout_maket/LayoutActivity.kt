package com.example.materialdesign.view.layout_maket

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityBottomNavigationBinding
import com.example.materialdesign.databinding.ActivityLayoutBinding
import com.example.materialdesign.databinding.ActivityNavigationBinding
import com.example.materialdesign.view.navigation.EarthFragment
import com.example.materialdesign.view.navigation.MarsFragment
import com.example.materialdesign.view.navigation.SystemFragment

class LayoutActivity : AppCompatActivity() {
    lateinit var binding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_constraint -> {
                    navigationTo(ConstraintFragment())
                    true
                }
                R.id.bottom_coordinator -> {

                    true
                }
                R.id.bottom_motion -> {

                    true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_constraint
    }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }
}