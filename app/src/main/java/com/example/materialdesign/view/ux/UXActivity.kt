package com.example.materialdesign.view.ux


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityMainBinding
import com.example.materialdesign.databinding.ActivityUxBinding
import com.example.materialdesign.view.MainActivity
import com.example.materialdesign.view.MainFragment


class UXActivity : AppCompatActivity() {
    lateinit var binding : ActivityUxBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationViewUX.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fragment_ux->{
                    navigateTo(UXFragment.newInstance())
                }
            }
            true
        }
    }

    fun navigateTo(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }

}