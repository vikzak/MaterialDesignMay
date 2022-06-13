package com.example.materialdesign.view.layout_maket.motion

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentConstraintBinding
import com.example.materialdesign.databinding.FragmentMotionStartBinding


class MotionFragment : Fragment() {

    private var _binding: FragmentMotionStartBinding? = null
    private val binding: FragmentMotionStartBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMotionStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        @JvmStatic
        fun newInstance() = MotionFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


