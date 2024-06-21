package com.project.joblense.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.project.joblense.data.MainRepository
import com.project.joblense.data.network.response.LoginResponse

import kotlinx.coroutines.launch
import com.project.joblense.data.Result
import com.project.joblense.data.model.User

class LoginViewModel(private val repository: MainRepository):ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> get() = _loginResult

    private val _saveSessionResult = MutableLiveData<Boolean>()
    val saveSessionResult: LiveData<Boolean> get() = _saveSessionResult

    fun saveSession(user: User) {
        viewModelScope.launch {
            try {
                repository.saveSession(user)
                _saveSessionResult.value = true
            } catch (e: Exception) {
                _saveSessionResult.value = false
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    _loginResult.value = Result(success = true, data = loginResponse)
                } else {
                    _loginResult.value = Result(success = false, error = response.message())
                }
            } catch (e: Exception) {
                _loginResult.value = Result(success = false, error = e.message.toString())
            }
        }
    }

}