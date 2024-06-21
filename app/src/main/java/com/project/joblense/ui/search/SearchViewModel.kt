package com.project.joblense.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    val selectedSkillList = MutableLiveData<ArrayList<String>>(arrayListOf())
}