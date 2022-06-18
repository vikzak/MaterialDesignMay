package com.example.materialdesign.view.ux

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentUxButtonBinding
import com.example.materialdesign.databinding.FragmentUxTextBinding


class ButtonUXFragment : Fragment() {

    private var _binding: FragmentUxButtonBinding? = null
    private val binding: FragmentUxButtonBinding
        get() = _binding!!


    companion object {
        fun newInstance() = ButtonUXFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUxButtonBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


