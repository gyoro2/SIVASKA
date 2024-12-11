package com.example.sivaska.ui.datakendaraan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataKendaraanViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Data Kendaraan"
    }
    val text: LiveData<String> = _text
}