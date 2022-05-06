package com.example.materialdesign.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import coil.load
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentMainBinding
import com.example.materialdesign.domain.NasaRepositoryImplementation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.flow.collect

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(NasaRepositoryImplementation())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.requestPictureOfTheDay()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)

        binding.textInput.setEndIconOnClickListener {
            //Toast.makeText(requireContext(),"Icon is Pressed", Toast.LENGTH_SHORT).show()
            ESIBottomSheetDialogFragment().show(
                parentFragmentManager,
                "ESIBottomSheetDialogFragment"
            )
        }
//        val sheetBehavior = BottomSheetBehavior.from<LinearLayout>(binding.layoutBottomSheetIncluded)
//        val behavior: BottomSheetBehavior<LinearLayout> = BottomSheetBehavior.from(binding.layoutBottomSheetIncluded)
//
//        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
//
//        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//            }
//
//        })

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.loading.collect {
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.error.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.image.collect { url ->
                url?.let {
                    binding.imgPhotoDay.load(it)
                }
            }
        }

    }
}