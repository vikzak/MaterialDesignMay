package com.example.materialdesign.view.layout_maket.animations

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityAnimationExplodeBinding
import com.example.materialdesign.databinding.ActivityAnimationResortBinding
import com.example.materialdesign.databinding.ActivityAnimationZoomBinding


class AnimationActivityResort : AppCompatActivity() {
    lateinit var binding: ActivityAnimationResortBinding
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationResortBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title: MutableList<String> = ArrayList()
        repeat(25){
            title.add("item $it")
        }
        binding.bottomAnimationResort.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.transitionContainer)
            title.shuffle()
            binding.transitionContainer.removeAllViews()
            title.forEach {
                binding.transitionContainer.addView(TextView(this).apply {
                    text = it
                    ViewCompat.setTransitionName(this,it)
                })
            }
        }
    }



    override fun onResume() {
        super.onResume()
    }
}