package com.project.joblense.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.joblense.databinding.ItemSkillsGridBinding
import com.project.joblense.databinding.ItemSkillsRowBinding
import com.project.joblense.utils.FakeData

class SearchQuerySkillsAdapter : RecyclerView.Adapter<SearchQuerySkillsAdapter.ViewHolder>() {
    var onSkillsAddClick: ((String) -> Unit)? = null

    private var skillsList = FakeData.skillList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSkillsRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = skillsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(skillsList[position])
    }

    inner class ViewHolder(private val binding: ItemSkillsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(skills: String) {
            binding.apply {
                tvSkills.text = skills

                root.setOnClickListener {
                    onSkillsAddClick?.invoke(skills)
                }
            }
        }
    }
}