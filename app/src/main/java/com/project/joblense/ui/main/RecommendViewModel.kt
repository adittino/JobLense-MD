package com.project.joblense.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.joblense.data.RecommendRepository
import com.project.joblense.data.network.response.History
import kotlinx.coroutines.launch

class RecommendViewModel(private val repository: RecommendRepository) : ViewModel() {

    private val _recommendResult = MutableLiveData<Result<List<String>>>()
    val recommendResult: LiveData<Result<List<String>>> = _recommendResult

    private val _historyResult = MutableLiveData<Result<List<History>>>()
    val historyResult: LiveData<Result<List<History>>> = _historyResult

    fun getRecommendations(skills: String) {
        viewModelScope.launch {
            try {
                val response = repository.getRecommend(skills)
                if (response.isSuccessful) {
                    response.body()?.recommendedJobs?.let { data ->
                        _recommendResult.value = Result.success(data)
                    } ?: run {
                        _recommendResult.value = Result.failure(Exception("No recommendations available"))
                    }
                } else {
                    _recommendResult.value = Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                _recommendResult.value = Result.failure(e)
            }
        }
    }

    fun getHistory() {
        viewModelScope.launch {
            try {
                val response = repository.getHistory()
                if (response.isSuccessful) {
                    response.body()?.history?.let { data ->
                        val dataList = data.toList()
                        _historyResult.value = Result.success(dataList)
                    } ?: run {
                        _historyResult.value = Result.failure(Exception("No History available"))
                    }
                } else {
                    _historyResult.value = Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                _historyResult.value = Result.failure(e)
            }
        }
    }

}