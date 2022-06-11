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

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.constraintContainer)

        binding.backgroundImage.setOnClickListener {
            val changeBounds = ChangeBounds()

            changeBounds.interpolator = AnticipateOvershootInterpolator(2.0f)
            changeBounds.duration = duration

            TransitionManager.beginDelayedTransition(binding.constraintContainer, changeBounds)
            flag = !flag
            if (flag) {
                constraintSet.connect(
                    R.id.title,
                    ConstraintSet.END,
                    R.id.constraint_container,
                    ConstraintSet.END
                )
                constraintSet.connect(
                    R.id.date,
                    ConstraintSet.END,
                    R.id.constraint_container,
                    ConstraintSet.END
                )

                constraintSet.applyTo(binding.constraintContainer)
            } else {
                constraintSet.connect(
                    R.id.title,
                    ConstraintSet.END,
                    R.id.backgroundImage,
                    ConstraintSet.START
                )
                constraintSet.connect(
                    R.id.date,
                    ConstraintSet.START,
                    R.id.title,
                    ConstraintSet.END
                )
                constraintSet.applyTo(binding.constraintContainer)
            }
        }
    }

}
