package com.project.joblense.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.joblense.databinding.ItemJobsRowBinding
import com.project.joblense.ui.detail.DetailActivity

class JobsAdapter : ListAdapter<String, JobsAdapter.JobViewHolder>(JobDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = getItem(position)
        holder.bind(job)
    }

    class JobViewHolder(private val binding: ItemJobsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: String) {
            binding.apply {
                tvJobName.text = job
                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra("job", job)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    class JobDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}