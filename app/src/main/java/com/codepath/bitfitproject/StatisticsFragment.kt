package com.codepath.bitfitproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


class StatisticsFragment : Fragment() {
    private lateinit var itemViewModel: ItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)

        // Initialize UI elements and handle statistics here
        val highestRepsTextView = view.findViewById<TextView>(R.id.highestRepsVal)
        val lowestRepsTextView = view.findViewById<TextView>(R.id.lowestRepsVal)
        val avgRepsTextView = view.findViewById<TextView>(R.id.averageRepsVal)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        // Observe LiveData for highest, lowest, and average calories
        itemViewModel.getMaxReps().observe(viewLifecycleOwner) { maxReps ->
            highestRepsTextView.text = "$maxReps"
        }

        itemViewModel.getMinReps().observe(viewLifecycleOwner) { minReps ->
            lowestRepsTextView.text = "$minReps"
        }

        itemViewModel.getAvgReps().observe(viewLifecycleOwner) { avgReps ->
            avgRepsTextView.text = "${String.format("%.2f", avgReps)}"
        }

        return view
    }


}