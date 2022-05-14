package com.example.materialdesign.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
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

    private var _binding:FragmentMainBinding?=null
    private val binding:FragmentMainBinding
        get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(NasaRepositoryImplementation())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
        //return super.onCreateView(inflater, container, savedInstanceState)
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
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://ru.wikipedia.org/wiki/${binding.textInputText.text.toString()}")
            })
        }

        // переход на википедию
        binding.textInput.setStartIconOnClickListener {
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

    companion object{
        fun newInstance() = MainFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


