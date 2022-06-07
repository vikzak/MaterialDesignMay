package com.example.materialdesign.view.layout_maket.animations

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
import com.example.materialdesign.databinding.ActivityAnimationExplodeBinding
import com.example.materialdesign.databinding.ActivityAnimationZoomBinding


class AnimationActivityZoom : AppCompatActivity() {
    lateinit var binding: ActivityAnimationZoomBinding
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationZoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageViewAnimationZoom.setOnClickListener {
            // добавляем анимацию (трансформацию) изображения
            val changeBounds = ChangeImageTransform()
            TransitionManager.beginDelayedTransition(binding.containerAnimationZoom,changeBounds)

            flag = !flag
            // без анимации:
            binding.imageViewAnimationZoom.scaleType =
                if (flag) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.CENTER_INSIDE
        }
    }


    override fun onResume() {
        super.onResume()
    }
}