package com.project.joblense.ui.history

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.joblense.R
import com.project.joblense.data.factory.ViewModelFactoryRecommendation
import com.project.joblense.databinding.ActivityHistoryBinding
import com.project.joblense.ui.adapters.HistoryAdapter
import com.project.joblense.ui.main.RecommendViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val historyAdapter = HistoryAdapter()
    private val viewModel: RecommendViewModel by viewModels {
        ViewModelFactoryRecommendation.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvJobsHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

//        initialState()
        getHistoryData()

    }

    private fun initialState() {
        binding.apply {
            progressbar.visibility = View.VISIBLE
            rvJobsHistory.visibility = View.GONE
        }
    }

    private fun getHistoryData() {
        viewModel.getHistory()
        viewModel.historyResult.observe(this) { result ->
//            binding.progressbar.visibility = View.GONE
            result.fold(
                onSuccess = { response ->
                    binding.rvJobsHistory.visibility = View.VISIBLE
                    historyAdapter.submitList(response)
                },
                onFailure = { error ->
                    binding.emptyLayout.apply {
                        root.visibility = View.VISIBLE
                        tvStatus.text = "History not found: ${error.message}"
                    }
                }
            )

        }
    }
}