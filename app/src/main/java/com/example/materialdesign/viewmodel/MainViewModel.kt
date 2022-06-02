package com.example.materialdesign.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.materialdesign.repository.NasaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(val repository: NasaRepository) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: Flow<Boolean> = _loading

    private val _image: MutableStateFlow<String?> = MutableStateFlow(null)
    val image: Flow<String?> = _image

    private val _textTitle: MutableStateFlow<String?> = MutableStateFlow(null)
    val textTitle: Flow<String?> = _textTitle

    private val _textExplanation: MutableStateFlow<String?> = MutableStateFlow(null)
    val textExplanation: Flow<String?> = _textExplanation

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: Flow<String> = _error

    fun requestPictureOfTheDay() {
        _loading.value = true //либо так:  _loading.emit(true)
        viewModelScope.launch {
            try {
                val url = repository.pictuteOfTheDay().hdurl
                val title = repository.pictuteOfTheDay().title
                val explanation = repository.pictuteOfTheDay().explanation
                _image.emit(url)
                _textTitle.emit(title)
                _textExplanation.emit(explanation)
            } catch (exc: IOException) {
                _error.emit("Network Error")
            }
            _loading.emit(false)
        }
    }

    fun requestPictureOfTheDayMars() {
        _loading.value = true //либо так:  _loading.emit(true)
        viewModelScope.launch {
            try {
                val url = repository.pictuteOfTheDayMars().hdurl
                val title = repository.pictuteOfTheDayMars().title
                val explanation = repository.pictuteOfTheDayMars().explanation
                _image.emit(url)
                _textTitle.emit(title)
                _textExplanation.emit(explanation)
            } catch (exc: IOException) {
                _error.emit("Network Error")
            }
            _loading.emit(false)
        }
    }
}

class MainViewModelFactory(private val repository: NasaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(repository) as T

}