package com.project.joblense.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.joblense.databinding.ItemJobsRowBinding
import com.project.joblense.databinding.ItemRequirementsLayoutBinding

class RequirementsAdapter : RecyclerView.Adapter<RequirementsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRequirementsLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(private val binding: ItemRequirementsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}