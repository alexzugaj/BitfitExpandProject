package com.codepath.bitfitproject

import android.view.LayoutInflater
import android.view.View
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class ExerciseFragment : Fragment() {
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.exercises)
        val mainActivity = activity as MainActivity

        itemViewModel = ViewModelProvider(mainActivity).get(ItemViewModel::class.java)

        itemViewModel.exerciseSetLiveData.observe(viewLifecycleOwner) { exerciseSets ->
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = ExerciseSetAdapter(exerciseSets, mainActivity)
        }

        return view
    }
}