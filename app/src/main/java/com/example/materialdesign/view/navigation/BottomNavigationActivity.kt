package com.example.materialdesign.view.navigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityBottomNavigationBinding
import com.example.materialdesign.databinding.ActivityNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
        badge.number = 1234
        badge.maxCharacterCount = 5
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_earth -> {
                    navigationTo(EarthFragment())
                    true
                }
                R.id.bottom_view_mars -> {
                    navigationTo(MarsFragment())
                    binding.bottomNavigationView.removeBadge(R.id.bottom_view_mars)
                    true
                }
                R.id.bottom_view_system -> {
                    navigationTo(SystemFragment())
                    true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_earth
    }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }
}