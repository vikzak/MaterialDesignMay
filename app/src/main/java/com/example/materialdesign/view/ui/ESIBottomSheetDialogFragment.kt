package com.example.materialdesign.view.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FaragmentDialogSheetBottomBinding
import com.example.materialdesign.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ESIBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FaragmentDialogSheetBottomBinding? = null
    private val binding: FaragmentDialogSheetBottomBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FaragmentDialogSheetBottomBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.faragment_dialog_sheet_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textExplanationDialogSheetBottom.typeface =
            Typeface.createFromAsset(requireContext().assets, "font/ofont.ru_Daray.ttf")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}