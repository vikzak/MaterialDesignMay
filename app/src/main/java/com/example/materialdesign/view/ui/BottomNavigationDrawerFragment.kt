package com.example.materialdesign.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.materialdesign.R
import com.example.materialdesign.databinding.BottomNavigationLayoutBinding
import com.example.materialdesign.view.layout_maket.LayoutActivity
import com.example.materialdesign.view.layout_maket.animations.*
import com.example.materialdesign.view.navigation.BottomNavigationActivity
import com.example.materialdesign.view.navigation.NavigationActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment: BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding: BottomNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_one -> {
                    startActivity(Intent(requireContext(),NavigationActivity::class.java))
                }
                R.id.navigation_two -> {
                    startActivity(Intent(requireContext(),BottomNavigationActivity::class.java))
                }
                R.id.navigation_three -> {
                    startActivity(Intent(requireContext(), LayoutActivity::class.java))
                }
                R.id.navigation_four -> {
                    startActivity(Intent(requireContext(), AnimationActivity::class.java))
                }
                R.id.navigation_five -> {
                    startActivity(Intent(requireContext(), AnimationActivityExplode::class.java))
                }
                R.id.navigation_six -> {
                    startActivity(Intent(requireContext(), AnimationActivityZoom::class.java))
                }
                R.id.navigation_seven -> {
                    startActivity(Intent(requireContext(), AnimationActivityResort::class.java))
                }
                R.id.navigation_eight -> {
                    startActivity(Intent(requireContext(), AnimationActivityFabRotation::class.java))
                }
                R.id.navigation_nine -> {
                    startActivity(Intent(requireContext(), AnimationActivityElevation::class.java))
                }
                R.id.navigation_ten -> {
                    startActivity(Intent(requireContext(), AnimationActivityBonus::class.java))
                }
            }
            dismiss()
            true
        }
    }

    private fun showMessage(s: String) {
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
