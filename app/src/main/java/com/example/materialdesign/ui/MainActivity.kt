package com.example.materialdesign.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityMainBinding


const val DefaultThemeIndex = 1
const val OrangeThemeIndex = 2
const val GreenThemeIndex = 3
const val KEY_SP = "sp"
const val KEY_CURRENT_THEME = "current_theme"

class MainActivity : AppCompatActivity(com.example.materialdesign.R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getRealStyleStyle(getCurrentTheme()))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(com.example.materialdesign.R.layout.activity_main)
        setContentView(binding.root)
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(com.example.materialdesign.R.id.fragment_container_view,MainFragment.newInstance())
                .commit()
        }
    }


    private fun getCurrentTheme(): Any {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    private fun getRealStyleStyle(currentTheme: Any): Int {
        return when (currentTheme) {
            DefaultThemeIndex -> com.example.materialdesign.R.style.MyDefaultTheme
            OrangeThemeIndex -> com.example.materialdesign.R.style.MyOrangeTheme
            GreenThemeIndex -> com.example.materialdesign.R.style.MyGreenTheme
            else -> 0
        }
    }

}