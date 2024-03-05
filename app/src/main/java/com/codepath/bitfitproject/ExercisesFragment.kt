package com.codepath.bitfitproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

private const val TAG = "ExercisesFragment"
class ExercisesFragment : Fragment() {
    private val exercises = mutableListOf<ExerciseSet>()
    private lateinit var exercisesRecyclerView: RecyclerView
    private lateinit var exerciseSetAdapter: ExerciseSetAdapter
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercises, container, false)

        exercisesRecyclerView = view.findViewById(R.id.exercise_recycler_view)
        val mainActivity = activity as MainActivity

        var itemViewModel = ViewModelProvider(mainActivity).get(ItemViewModel::class.java)

        itemViewModel.exerciseSetLiveData.observe(viewLifecycleOwner) { exerciseSets ->
            exercisesRecyclerView.layoutManager = LinearLayoutManager(context)
            //exercisesRecyclerView.setHasFixedSize(true)
            exercisesRecyclerView.adapter = ExerciseSetAdapter(exerciseSets, mainActivity)
        }


        return view
    }
    /*
    private fun fetchExercises() {

        lifecycleScope.launch {
            (application as ExerciseApplication).db.exerciseDao().getAll()
                .collect { databaseList ->
                    databaseList.map { entity ->
                        ExerciseSet(
                            entity.exerciseName,
                            entity.reps
                        )
                    }.also { mappedList ->
                        exercises.clear()
                        exercises.addAll(mappedList)
                        exerciseSetAdapter.notifyDataSetChanged()
                    }
                }
        }
    }

     */
    companion object {
        fun newInstance() : ExercisesFragment {
            return ExercisesFragment()
        }
    }
}