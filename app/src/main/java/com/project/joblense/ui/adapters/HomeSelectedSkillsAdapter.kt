package com.project.joblense.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.joblense.databinding.ItemSelectedSkillsHomeGridBinding
import com.project.joblense.databinding.ItemSkillsGridBinding

class HomeSelectedSkillsAdapter : RecyclerView.Adapter<HomeSelectedSkillsAdapter.ViewHolder>() {
    private var skillsList: ArrayList<String> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(skillsList: ArrayList<String>) {
        this.skillsList = skillsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSelectedSkillsHomeGridBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemSelectedSkillsHomeGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(skills: String) {
            binding.tvKeyword.text = skills
        }
    }
}