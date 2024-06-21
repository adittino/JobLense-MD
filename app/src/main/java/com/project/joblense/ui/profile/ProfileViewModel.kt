package com.project.joblense.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.joblense.data.MainRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: MainRepository) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}