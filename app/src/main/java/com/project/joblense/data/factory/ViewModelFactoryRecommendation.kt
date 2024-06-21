package com.project.joblense.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.joblense.data.MainRepository
import com.project.joblense.data.RecommendRepository
import com.project.joblense.data.di.Injection
import com.project.joblense.data.di.RecommendInjection
import com.project.joblense.ui.auth.login.LoginViewModel
import com.project.joblense.ui.auth.register.RegisterViewModel
import com.project.joblense.ui.main.MainViewModel
import com.project.joblense.ui.main.RecommendViewModel

class ViewModelFactoryRecommendation(private val repository: RecommendRepository) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(RecommendViewModel::class.java) -> {
                RecommendViewModel(repository) as T
            }



            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        private var instance: ViewModelFactoryRecommendation? = null

        fun getInstance(context: Context): ViewModelFactoryRecommendation {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactoryRecommendation(RecommendInjection.provideRepository(context)).also { instance = it }
            }
        }
    }
}