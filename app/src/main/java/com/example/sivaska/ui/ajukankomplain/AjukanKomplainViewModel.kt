package com.example.sivaska.ui.ajukankomplain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AjukanKomplainViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ajukan Komplain"
    }
    val text: LiveData<String> = _text
}