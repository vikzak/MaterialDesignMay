package com.example.materialdesign.view.layout_maket.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityAnimationElevationBinding
import com.example.materialdesign.databinding.ActivityAnimationExplodeBinding
import com.example.materialdesign.databinding.ActivityAnimationFabRotationBinding
import com.example.materialdesign.databinding.ActivityAnimationZoomBinding


class AnimationActivityElevation : AppCompatActivity() {

    val duration = 1200L
    lateinit var binding: ActivityAnimationElevationBinding
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationElevationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
        }

    }

    override fun onResume() {
        super.onResume()
    }
}