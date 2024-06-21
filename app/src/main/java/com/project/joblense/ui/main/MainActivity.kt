package com.project.joblense.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.joblense.data.factory.ViewModelFactory
import com.project.joblense.data.factory.ViewModelFactoryRecommendation
import com.project.joblense.databinding.ActivityMainBinding
import com.project.joblense.ui.adapters.HomeSelectedSkillsAdapter
import com.project.joblense.ui.adapters.JobsAdapter
import com.project.joblense.ui.auth.AuthActivity
import com.project.joblense.ui.detail.DetailActivity
import com.project.joblense.ui.history.HistoryActivity
import com.project.joblense.ui.profile.ProfileActivity
import com.project.joblense.ui.search.SearchActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val jobsAdapter = JobsAdapter()
    private val selectedSkillsAdapter = HomeSelectedSkillsAdapter()
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    private val recommendViewModel: RecommendViewModel by viewModels {
        ViewModelFactoryRecommendation.getInstance(this)
    }
    private var token = ""
    private var name = ""

    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            binding.emptyLayout.root.visibility = View.GONE
            binding.progressbar.visibility = View.VISIBLE

            val skillsList =
                result.data?.getStringArrayListExtra(SearchActivity.EXTRA_SELECTED_SKILLS)
            val randomSkill = skillsList?.random()

            binding.btnSearch.isVisible = skillsList.isNullOrEmpty()
            binding.rvSelectedSkills.isVisible = !skillsList.isNullOrEmpty()

            skillsList?.let {
                binding.rvSelectedSkills.apply {
                    selectedSkillsAdapter.setList(it)
                    adapter = selectedSkillsAdapter
                    layoutManager = GridLayoutManager(this@MainActivity, 4)
                }
            }

            randomSkill?.let {
                recommendViewModel.getRecommendations(it)
                recommendViewModel.recommendResult.observe(this) { result ->
                    binding.progressbar.visibility = View.GONE
                    result.fold(
                        onSuccess = { response ->
                            binding.rvJobs.visibility = View.VISIBLE
                            jobsAdapter.submitList(response)
                        },
                        onFailure = { error ->
                            binding.emptyLayout.apply {
                                root.visibility = View.VISIBLE
                                tvStatus.text = "Job not found: ${error.message}"
                            }
                        }
                    )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSeeHistory.setOnClickListener{
            startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
        }

        setRecyclerViews()
        setListeners()
        getSession()
    }

    private fun getSession() {
        viewModel.getSession().observe(this) { user ->
            token = user.token
            if (!user.isLogin) {
                movesToAuth()
            }
            binding.tvName.text = user.email
            this.name = user.email
        }
    }

    private fun setRecyclerViews() {
        binding.apply {
            rvJobs.apply {
                adapter = jobsAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            layoutLabels.setOnClickListener {
                activityResultLauncher.launch(Intent(this@MainActivity, SearchActivity::class.java))
            }

            ivProfile.setOnClickListener {
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
    }

    private fun movesToAuth() {
        Intent(this, AuthActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }
}