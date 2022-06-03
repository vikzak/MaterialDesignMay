package com.example.materialdesign.view.layout_maket.constraint

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentConstraintBinding


class ConstraintFragment : Fragment() {

    private var _binding: FragmentConstraintBinding? = null
    private val binding: FragmentConstraintBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        @JvmStatic
        fun newInstance() = ConstraintFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


