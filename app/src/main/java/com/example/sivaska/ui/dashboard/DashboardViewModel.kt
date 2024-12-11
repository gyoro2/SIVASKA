package com.example.sivaska.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hi, Supir 👋" // Sesuaikan teks di sini
    }
    val text: LiveData<String> = _text
}