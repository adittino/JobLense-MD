package com.project.joblense.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.joblense.data.network.response.History
import com.project.joblense.databinding.ItemHistoryRowBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HistoryAdapter : ListAdapter<History, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryRowBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(history: History) {
            binding.tvSkill.text = history.skills
            binding.tvTime.text = timestampIntoDate(history.timestamp)
            binding.tvJobs.text = history.recommendedJobs.joinToString("\n") { "• $it" }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun timestampIntoDate(timeStamp: String) : String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        val dateTime = LocalDateTime.parse(timeStamp, formatter)
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = dateTime.format(outputFormatter)

        return formattedDateTime
    }

    class HistoryDiffCallback : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem.skills == newItem.skills
        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem == newItem
        }
    }
}