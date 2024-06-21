package com.project.joblense.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.joblense.data.MainRepository
import com.project.joblense.data.di.Injection
import com.project.joblense.ui.auth.AuthViewModel
import com.project.joblense.ui.auth.login.LoginViewModel
import com.project.joblense.ui.auth.register.RegisterViewModel
import com.project.joblense.ui.main.MainViewModel
import com.project.joblense.ui.main.RecommendViewModel
import com.project.joblense.ui.profile.ProfileViewModel

class ViewModelFactory (private val repository: MainRepository) : ViewModelProvider.Factory  {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }


            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).also { instance = it }
            }
        }
    }

}