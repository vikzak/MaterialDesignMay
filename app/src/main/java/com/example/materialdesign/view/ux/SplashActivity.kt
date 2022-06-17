package com.example.materialdesign.view.ux


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityMainBinding
import com.example.materialdesign.view.MainActivity
import com.example.materialdesign.view.MainFragment


class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(getRealStyleStyle(getCurrentTheme()))
        //setTheme(R.style.MyOrangeTheme)
        setContentView(R.layout.activity_splash)

        findViewById<ImageView>(R.id.splash_IV).animate().rotationBy(720f).setDuration(3000L).start()
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000L)

    }

}