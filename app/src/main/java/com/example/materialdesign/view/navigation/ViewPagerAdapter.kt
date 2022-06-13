package com.example.materialdesign.view.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

const val EARTH_KEY = 0
const val MARS_KEY = 1
const val SYSTEM_KEY = 2

class ViewPagerAdapter(
    private val fragmentManager: FragmentActivity
) : FragmentStateAdapter(fragmentManager) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

    // использование ViewPager2
    override fun getItemCount() = fragments.size
    override fun createFragment(position: Int) = fragments[position]

// использование ViewPager
//    override fun getCount() = fragments.size
//
//    override fun getItem(position: Int): Fragment {
//        return fragments[position]
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return when(position){
//            EARTH_KEY -> "Earth"
//            MARS_KEY -> "Mars"
//            SYSTEM_KEY -> "System"
//            else -> "Earth"
//        }
//    }
}