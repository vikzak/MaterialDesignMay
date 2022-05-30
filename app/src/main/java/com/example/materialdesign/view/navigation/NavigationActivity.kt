package com.example.materialdesign.view.navigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityNavigationBinding

class NavigationActivity:AppCompatActivity() {
    lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        binding.tabLayout.setupWithViewPager(binding.viewPager)
//        binding.tabLayout.getTabAt(EARTH_KEY)?.setIcon(R.drawable.ic_earth)
//        binding.tabLayout.getTabAt(MARS_KEY)?.setIcon(R.drawable.ic_mars)
//        binding.tabLayout.getTabAt(SYSTEM_KEY)?.setIcon(R.drawable.ic_system)

        //далее CustomView
        binding.tabLayout.getTabAt(EARTH_KEY)?.setCustomView(R.layout.activity_navigation_tablayout_item_eath)
        binding.tabLayout.getTabAt(MARS_KEY)?.setCustomView(R.layout.activity_navigation_tablayout_item_mars)
        binding.tabLayout.getTabAt(SYSTEM_KEY)?.setCustomView(R.layout.activity_navigation_tablayout_item_system)
    }
}