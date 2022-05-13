package com.example.materialdesign.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import coil.load
import com.example.materialdesign.R
import com.example.materialdesign.api.PictureOfTheDayResponse
import com.example.materialdesign.databinding.FragmentMainBinding
import com.example.materialdesign.domain.NasaRepository
import com.example.materialdesign.domain.NasaRepositoryImplementation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        binding.bottomAppbar.setOnMenuItemClickListener {
            when (it.itemId) {
                else -> true
            }
        }

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            Toast.makeText(requireContext(), checkedId.toString(), Toast.LENGTH_SHORT).show()
        }


        binding.chipDay.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.chipDay.isChecked) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
                Toast.makeText(requireContext(), current.format(formatter), Toast.LENGTH_SHORT)
                    .show()
            }
        } // тут еще не сообразил как передвать любую дату в строку вызова

        binding.textInput.setEndIconOnClickListener {
            //Toast.makeText(requireContext(),"Icon is Pressed", Toast.LENGTH_SHORT).show()
            ESIBottomSheetDialogFragment().show(
                parentFragmentManager,
                "ESIBottomSheetDialogFragment"
            )
        }

        // переход на википедию
        binding.textInput.setStartIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://ru.wikipedia.org/wiki/${binding.textInputText.text.toString()}")
            })
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

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.textTitle.collect { title ->
                title?.let {
                    binding.textTitle.setText(it)
                }
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.textExplanation.collect { explanation ->
                explanation?.let {
                    binding.textExplanation.setText(it)
                }
            }
        }
    }
}


