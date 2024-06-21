package com.project.joblense.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.joblense.data.MainRepository
import com.project.joblense.data.model.User

class AuthViewModel(private val repository: MainRepository) : ViewModel() {

    fun getSession(): LiveData<User> {
        return repository.getSession().asLiveData()
    }
}