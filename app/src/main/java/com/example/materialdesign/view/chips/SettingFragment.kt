package com.example.materialdesign.view.chips

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentSettingBinding
import com.example.materialdesign.view.*

class SettingFragment : Fragment(), View.OnClickListener {
    //    private val KEY_SP = "sp"
//    private val KEY_CURRENT_THEME = "current_theme"
    // для изменений в локальной теме
    private val KEY_SP_LOCAL = "sp_local"
    private val KEY_CURRENT_THEME_LOCAL = "current_theme_local"

    private lateinit var parentActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = activity as MainActivity // воторой способ
    }

    private var _binding: FragmentSettingBinding? = null
    private val binding: FragmentSettingBinding
        get() = _binding!!

    companion object {
        fun newInstance() = SettingFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context: Context =
            ContextThemeWrapper(activity, getRealStyleLocal(getCurrentThemeLocal()))
        val localInflater = inflater.cloneInContext(context)
        //_binding = FragmentSettingBinding.inflate(inflater, container, false)
        //_binding = FragmentSettingBinding.inflate(inflater)
        _binding = FragmentSettingBinding.inflate(localInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dayChip.setOnClickListener {
            if (binding.dayChip.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        binding.nightChip.setOnClickListener {
            if (binding.nightChip.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        binding.themeDefault.setOnClickListener(this)
        binding.themeOrange.setOnClickListener(this)
        binding.themeGreen.setOnClickListener(this)
        when (getCurrentThemeLocal()) {
            1 -> binding.themeDefault.isChecked = true
            2 -> binding.themeOrange.isChecked = true
            3 -> binding.themeGreen.isChecked = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.theme_default -> {
                setCurrentThemeLocal(DefaultThemeIndex)
                parentActivity.setTheme(R.style.MyDefaultTheme)
                parentActivity.recreate()
            }
            R.id.theme_orange -> {
                setCurrentThemeLocal(OrangeThemeIndex)
                parentActivity.setTheme(R.style.MyOrangeTheme)
                parentActivity.recreate()
            }
            R.id.theme_green -> {
                setCurrentThemeLocal(GreenThemeIndex)
                parentActivity.setTheme(R.style.MyGreenTheme)
                parentActivity.recreate()
            }
        }
    }

    private fun setCurrentThemeLocal(currentTheme: Int) {
//        val sharedPreferences = // для темы фрагмента
//            requireActivity().getSharedPreferences(KEY_SP_LOCAL, AppCompatActivity.MODE_PRIVATE)
        val sharedPreferences =
            requireActivity().getSharedPreferences(KEY_SP, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        //editor.putInt(KEY_CURRENT_THEME_LOCAL, currentTheme) // для темы фрагмента
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }

    private fun getCurrentThemeLocal(): Int {
        val sharedPreferences = //здесь тоже можно указать локлаьные данные для темы фрагмента
            requireActivity().getSharedPreferences(KEY_SP, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    private fun getRealStyleLocal(currentTheme: Int): Int {
        return when (currentTheme) {
            DefaultThemeIndex -> R.style.MyDefaultTheme
            OrangeThemeIndex -> R.style.MyOrangeTheme
            GreenThemeIndex -> R.style.MyGreenTheme
            else -> 0
        }
    }
}