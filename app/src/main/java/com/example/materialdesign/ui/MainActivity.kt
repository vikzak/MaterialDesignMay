package com.example.materialdesign.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.materialdesign.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // задаем тему
        setTheme(R.style.MyDefaultTheme)
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view,MainFragment.newInstance())
                .commit()
        }
    }

}