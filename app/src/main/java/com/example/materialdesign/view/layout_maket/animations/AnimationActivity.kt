package com.example.materialdesign.view.layout_maket.animations

import android.os.Bundle
import android.os.Handler
import androidx.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionSet
import com.example.materialdesign.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnimationBinding
    var animationFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomAcbAnimation.setOnClickListener {
            val transition = TransitionSet()
            val fade = Fade()
            val slide = Slide()

            val changeBounds = ChangeBounds()
            animationFlag = !animationFlag
            //fade.duration = 5000
            slide.duration = 5000
            changeBounds.duration = 2000

            //transition.ordering =TransitionSet.ORDERING_SEQUENTIAL
            transition.ordering =TransitionSet.ORDERING_TOGETHER
            //transition.addTransition(fade)
            transition.addTransition(slide)
            transition.addTransition(changeBounds)
            TransitionManager.beginDelayedTransition(binding.animationContainer,transition)
            binding.textViexActwAnimation.visibility =
                if (animationFlag) View.VISIBLE else View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        Handler(mainLooper).postDelayed({
            val transition = TransitionSet()
            val fade = Fade()
            val slide = Slide()

            val changeBounds = ChangeBounds()
            animationFlag = !animationFlag
            //fade.duration = 5000
            slide.duration = 3000
            changeBounds.duration = 1000

            //transition.ordering =TransitionSet.ORDERING_SEQUENTIAL
            transition.ordering =TransitionSet.ORDERING_TOGETHER
            //transition.addTransition(fade)
            transition.addTransition(slide)
            transition.addTransition(changeBounds)
            TransitionManager.beginDelayedTransition(binding.animationContainer,transition)
            binding.textViexActwAnimation.visibility =
                if (animationFlag) View.VISIBLE else View.GONE
        }, 1000)
    }
}