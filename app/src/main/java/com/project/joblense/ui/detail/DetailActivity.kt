package com.project.joblense.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.joblense.databinding.ActivityDetailBinding
import com.project.joblense.ui.adapters.RequirementsAdapter
import com.project.joblense.ui.adapters.SkillsAdapter

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val requirementsAdapter = RequirementsAdapter()
    private val skillsAdapter = SkillsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val job = intent.getStringExtra("job")
        binding.tvJobName.text = job

        setRecyclerViews()
        setListeners()
    }

    private fun setRecyclerViews() {
        binding.apply {
            rvRequirements.apply {
                adapter = requirementsAdapter
                layoutManager = LinearLayoutManager(this@DetailActivity)
            }

            rvSkills.apply {
                adapter = skillsAdapter
                layoutManager = GridLayoutManager(this@DetailActivity, 2)
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}