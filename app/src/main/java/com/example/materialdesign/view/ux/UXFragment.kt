package com.example.materialdesign.view.ux

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import coil.load
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentMainBinding
import com.example.materialdesign.databinding.FragmentUxBinding
import com.example.materialdesign.repository.NasaRepositoryImplementation
import com.example.materialdesign.view.chips.SettingFragment
import com.example.materialdesign.view.layout_maket.animations.AnimationActivityBonus
import com.example.materialdesign.view.recycler_view.RecyclerActivity
import com.example.materialdesign.view.ui.BottomNavigationDrawerFragment
import com.example.materialdesign.view.ui.ESIBottomSheetDialogFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior


class UXFragment : Fragment() {

    private var _binding: FragmentUxBinding? = null
    private val binding: FragmentUxBinding
        get() = _binding!!


    companion object {
        fun newInstance() = UXFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUxBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


