package com.example.materialdesign.view.navigation

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import coil.load
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentEarthBinding
import com.example.materialdesign.databinding.FragmentMainBinding
import com.example.materialdesign.repository.NasaRepositoryImplementation
import com.example.materialdesign.view.chips.SettingFragment
import com.example.materialdesign.view.ui.BottomNavigationDrawerFragment
import com.example.materialdesign.view.ui.ESIBottomSheetDialogFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior


class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding: FragmentEarthBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        @JvmStatic
        fun newInstance() = EarthFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


