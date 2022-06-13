package com.example.materialdesign.view.navigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityNavigationBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class NavigationActivity:AppCompatActivity() {
    lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager) // для ViewPager
        //binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.adapter = ViewPagerAdapter(this) //для ViewPager2
        TabLayoutMediator(binding.tabLayout,binding.viewPager, object :TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = when(position){
                    EARTH_KEY -> "Earth"
                    MARS_KEY -> "Mars"
                    SYSTEM_KEY -> "System"
                    else -> "Earth"
                }
            }
        }).attach()

//        binding.tabLayout.getTabAt(EARTH_KEY)?.setIcon(R.drawable.ic_earth)
//        binding.tabLayout.getTabAt(MARS_KEY)?.setIcon(R.drawable.ic_mars)
//        binding.tabLayout.getTabAt(SYSTEM_KEY)?.setIcon(R.drawable.ic_system)

        //далее CustomView
//        binding.tabLayout.getTabAt(EARTH_KEY)?.setCustomView(R.layout.activity_navigation_tablayout_item_eath)
//        binding.tabLayout.getTabAt(MARS_KEY)?.setCustomView(R.layout.activity_navigation_tablayout_item_mars)
//        binding.tabLayout.getTabAt(SYSTEM_KEY)?.setCustomView(R.layout.activity_navigation_tablayout_item_system)
    }
}