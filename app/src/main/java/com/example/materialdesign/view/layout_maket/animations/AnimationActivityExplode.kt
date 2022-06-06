package com.example.materialdesign.view.layout_maket.animations

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityAnimationExplodeBinding


class AnimationActivityExplode : AppCompatActivity() {
    lateinit var binding: ActivityAnimationExplodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationExplodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = Adapter()
    }


    private fun explode(clikedView: View) {
        val explode = Explode()
        explode.duration = 1500
        // центр взрыва
        val rectange = Rect()
        clikedView.getGlobalVisibleRect(rectange)
        // исключаем квадрат по которому кликнули
        explode.excludeTarget(clikedView,true)
        // оставляем его
        val transitionSet = TransitionSet()
        val fade = Fade()
        fade.duration = 3000
        transitionSet.addTransition(explode)
        transitionSet.addTransition(fade)
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return rectange
            }
        }
        //TransitionManager.beginDelayedTransition(binding.recyclerView, explode)
        TransitionManager.beginDelayedTransition(binding.recyclerView, transitionSet)
        binding.recyclerView.adapter = null
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animation_explode_recycler_item,
                    parent,
                    false
                ) as View
            )
        }


        override fun getItemCount(): Int {
            return 32
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                explode(it)
            }
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onResume() {
        super.onResume()
    }
}