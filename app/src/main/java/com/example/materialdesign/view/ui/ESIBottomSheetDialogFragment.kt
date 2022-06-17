package com.example.materialdesign.view.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        binding.textExplanationDialogSheetBottom.text =
            "Тестируем работу spannableStringBuilder и прочих.\n ${binding.textExplanationDialogSheetBottom.text}"
        val text = binding.textExplanationDialogSheetBottom.text
        binding.textExplanationDialogSheetBottom.typeface =
            Typeface.createFromAsset(requireContext().assets, "font/ofont.ru_Daray.ttf")

        var spannableStringBuilder = SpannableStringBuilder(text)
        val spannableString = SpannableString(text)
        val spannedString = SpannedString(spannableStringBuilder)
        binding.textExplanationDialogSheetBottom.setText(spannableStringBuilder,TextView.BufferType.EDITABLE)
        spannableStringBuilder = binding.textExplanationDialogSheetBottom.text as SpannableStringBuilder

        //spannableStringBuilder.insert(3,"\n")
        spannableStringBuilder.insert(10,"\n")
        spannableStringBuilder.setSpan(
            BulletSpan(
                15,
                ContextCompat.getColor(requireContext(), R.color.paletteorange_800,)
            ,20), 0, 11, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableStringBuilder.setSpan(
            ImageSpan(requireContext(), R.drawable.ic_mars), 0, 11, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableStringBuilder.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.paletteblue_700)
            ), 10, spannableStringBuilder.length/2, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.paletteorange_800)
            ), spannableStringBuilder.length/2, spannableStringBuilder.length, SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannableStringBuilder.insert(spannableStringBuilder.length,"\nвставляем текст в самый конец и смотрим чтобы он закрасился")


        spannableStringBuilder.indices.forEach {
            if ((spannableStringBuilder[it]) == ('е')) {
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(requireContext(),
                        R.color.colorAccent
                    )
                    ), it, it+1, SpannableString.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


