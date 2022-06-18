package com.example.materialdesign.view.ux

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.materialdesign.databinding.FragmentUxTextBinding


class TextUXFragment : ViewBindingFragment<FragmentUxTextBinding>(FragmentUxTextBinding::inflate) {
    companion object {
        fun newInstance() = TextUXFragment()
    }
}


