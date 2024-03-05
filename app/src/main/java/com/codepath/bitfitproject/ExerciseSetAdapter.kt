package com.codepath.bitfitproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.bitfitproject.databinding.ExerciseSetCellBinding

class ExerciseSetAdapter (
    private val exercises: List<ExerciseSet>,
    private val clickListener: MainActivity) :
    RecyclerView.Adapter<ExerciseSetAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseSetAdapter.ViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ExerciseSetCellBinding.inflate(from, parent, false)
        return ViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ExerciseSetAdapter.ViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount() = exercises.size
    inner class ViewHolder(
        private val context: Context,
        private val binding: ExerciseSetCellBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: ExerciseSet) {
            binding.exerciseName.text = exercise.exerciseName
            binding.repsValue.text = exercise.reps.toString()
        }
    }
}