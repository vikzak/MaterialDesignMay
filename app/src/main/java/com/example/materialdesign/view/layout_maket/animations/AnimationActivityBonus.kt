package com.example.materialdesign.view.layout_maket.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.materialdesign.R
import com.example.materialdesign.databinding.*


class AnimationActivityBonus : AppCompatActivity() {

    val duration = 1200L
    lateinit var binding: ActivityAnimationBonusStartBinding
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBonusStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backgroundImage.setOnClickListener {
            val changeBounds=ChangeBounds()
            val  constraintSet = ConstraintSet()
            flag = !flag
            if (flag){
                changeBounds.interpolator = AnticipateOvershootInterpolator(2.0f)
                changeBounds.duration = duration
                TransitionManager.beginDelayedTransition(binding.constraintContainer,changeBounds)

                constraintSet.clone(this, R.layout.activity_animation_bonus_end)
                constraintSet.applyTo(binding.constraintContainer)
            } else {
                changeBounds.interpolator = AnticipateOvershootInterpolator(2.0f)
                changeBounds.duration = duration
                TransitionManager.beginDelayedTransition(binding.constraintContainer,changeBounds)
                constraintSet.clone(this, R.layout.activity_animation_bonus_start)
                constraintSet.applyTo(binding.constraintContainer)
            }
        }
    }

}
