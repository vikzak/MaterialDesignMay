package com.example.materialdesign.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ESIBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.faragment_dialog_sheet_bottom, container, false)
    }
}