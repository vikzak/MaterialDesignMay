package com.example.materialdesign.view.ux

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentUxButtonBinding
import com.example.materialdesign.databinding.FragmentUxTextBinding


class ButtonUXFragment : ViewBindingFragment<FragmentUxButtonBinding>(FragmentUxButtonBinding::inflate) {
    companion object {
        fun newInstance() = ButtonUXFragment()
    }
}


