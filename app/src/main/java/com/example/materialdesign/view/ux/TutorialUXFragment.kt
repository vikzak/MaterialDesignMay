package com.example.materialdesign.view.ux

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentUxButtonBinding
import com.example.materialdesign.databinding.FragmentUxTextBinding
import com.example.materialdesign.databinding.FragmentUxTutorialBinding
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener


class TutorialUXFragment : ViewBindingFragment<FragmentUxTutorialBinding>(FragmentUxTutorialBinding::inflate) {
    companion object {
        fun newInstance() = TutorialUXFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val builder = GuideView.Builder(requireContext())
            .setTitle("Устаревший подход")
            .setContentText("Мы не используем работу с цветом, размером и шрифтами")
            .setGravity(Gravity.center)
            .setDismissType(DismissType.anywhere)
            .setTargetView(binding.badButton)
            .setDismissType(DismissType.anywhere)
            .setGuideListener(object : GuideListener {
                override fun onDismiss(view: View?) {
                    val builder2 = GuideView.Builder(requireContext())
                        .setTitle("Устаревший подход")
                        .setContentText("Весь блок выполнен в устаревшем дизайне")
                        .setGravity(Gravity.center)
                        .setDismissType(DismissType.anywhere)
                        .setTargetView(binding.badBlock)
                        .setDismissType(DismissType.anywhere)
                        .setGuideListener(object : GuideListener {
                            override fun onDismiss(view: View?) {
                                val builder3 = GuideView.Builder(requireContext())
                                    .setTitle("Новый(material) подход")
                                    .setContentText("Мы используем работу ТОЛЬКО с прозрачностями и пространством")
                                    .setGravity(Gravity.center)
                                    .setDismissType(DismissType.anywhere)
                                    .setTargetView(binding.goodBlock)
                                    .setDismissType(DismissType.anywhere)
                                    .setGuideListener(object : GuideListener {
                                        override fun onDismiss(view: View?) {
                                            // здесь можно сохранить в SP то что уже Tutorial показан
                                        }
                                    })
                                builder3.build().show()
                            }
                        })
                    builder2.build().show()
                }
            })
        builder.build().show()

    }

}


