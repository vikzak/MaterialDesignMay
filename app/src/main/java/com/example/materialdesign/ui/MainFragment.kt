package com.example.materialdesign.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import coil.load
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentMainBinding
import com.example.materialdesign.domain.NasaRepositoryImplementation
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(NasaRepositoryImplementation())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.requestPictureOfTheDay()
        }
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        binding.bottomAppbar.setOnMenuItemClickListener {
            when (it.itemId) {
                else -> true
            }
        }

        binding.textInput.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://ru.wikipedia.org/wiki/${binding.textInputText.text.toString()}")
            })
        }

//        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
//            Toast.makeText(requireContext(), checkedId.toString(), Toast.LENGTH_SHORT).show()
//        }


//        binding.chipDay.setOnCheckedChangeListener { compoundButton, b ->
//            if (binding.chipDay.isChecked) {
//                val current = LocalDateTime.now()
//                val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
//                Toast.makeText(requireContext(), current.format(formatter), Toast.LENGTH_SHORT)
//                    .show()
//            }
//        } // тут еще не сообразил как передвать любую дату в строку вызова

        binding.textInput.setStartIconOnClickListener {
            //Toast.makeText(requireContext(),"Icon is Pressed", Toast.LENGTH_SHORT).show()
            ESIBottomSheetDialogFragment().show(
                parentFragmentManager,
                "ESIBottomSheetDialogFragment"
            )
        }

        bottomSheetBehavior =
            BottomSheetBehavior.from(binding.layoutBottomSheetIncluded.layotBottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_DRAGGING

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
                    binding.layoutBottomSheetIncluded.headerTextView.setText(it)
                }
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.textExplanation.collect { explanation ->
                explanation?.let {
                    binding.layoutBottomSheetIncluded.descriptionTextView.setText(it)
                }
            }
        }
        // объявляем наш appbar (т.к. он custom)
        setHasOptionsMenu(true)
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppbar)
        binding.fab.setOnClickListener {
            if (isMain) {
                binding.bottomAppbar.navigationIcon = null
                binding.bottomAppbar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_back_fab))
                binding.bottomAppbar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                binding.bottomAppbar.navigationIcon = ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger_menu_bottom_bar)
                binding.bottomAppbar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(),android.R.drawable.btn_star_big_on))
                binding.bottomAppbar.replaceMenu(R.menu.menu_bottom_appbar)
            }
            isMain = !isMain
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_appbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(
                requireContext(),
                "click favorite icon",
                Toast.LENGTH_SHORT
            ).show()
            R.id.app_bar_setting -> Toast.makeText(
                requireContext(),
                "click setting icon",
                Toast.LENGTH_SHORT
            ).show()
            //android.R.id.home -> Toast.makeText(requireContext(),"click home (burger)", Toast.LENGTH_SHORT).show()
            android.R.id.home -> BottomNavigationDrawerFragment().show(
                requireActivity().supportFragmentManager,
                ""
            )
        }
        return super.onOptionsItemSelected(item)
    }

    // анимируем fab для этого заводим переменную isMain
    var isMain: Boolean = true


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


