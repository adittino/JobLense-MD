package com.project.joblense.ui.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.joblense.databinding.ActivitySearchBinding
import com.project.joblense.ui.adapters.SearchQuerySkillsAdapter
import com.project.joblense.ui.adapters.SearchSelectedSkillsAdapter

class SearchActivity : AppCompatActivity() {
    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var binding: ActivitySearchBinding

    private val querySkillsAdapter = SearchQuerySkillsAdapter()
    private val selectedSkillsAdapter = SearchSelectedSkillsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModels()
        setRecyclerViews()
        setListeners()
    }

    private fun observeViewModels() {
        viewModel.selectedSkillList.observe(this) {
            selectedSkillsAdapter.setList(it)
        }
    }

    private fun setRecyclerViews() {
        binding.apply {
            rvSkills.apply {
                adapter = querySkillsAdapter
                layoutManager = LinearLayoutManager(this@SearchActivity)
            }

            rvSelectedSkills.apply {
                adapter = selectedSkillsAdapter
                layoutManager = GridLayoutManager(this@SearchActivity, 3)
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            querySkillsAdapter.onSkillsAddClick = { skills ->
                val skillList = viewModel.selectedSkillList.value
                skillList?.add(skills)
                viewModel.selectedSkillList.postValue(skillList)
            }

            selectedSkillsAdapter.onSkillsDeleteClick = { skills ->
                val skillList = viewModel.selectedSkillList.value
                skillList?.remove(skills)
                viewModel.selectedSkillList.postValue(skillList)
                viewModel.selectedSkillList.value!!.remove(skills)
            }

            btnSearch.setOnClickListener {
                val intent = Intent()
                val skillList = viewModel.selectedSkillList.value
                intent.putStringArrayListExtra(EXTRA_SELECTED_SKILLS, skillList)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_SELECTED_SKILLS = "extra_selected_skills"
    }
}