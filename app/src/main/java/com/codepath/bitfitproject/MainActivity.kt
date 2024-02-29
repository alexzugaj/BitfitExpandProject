package com.codepath.bitfitproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.lifecycle.observe
import com.codepath.bitfitproject.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {
    private val exercises = mutableListOf<ExerciseSet>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val exerciseApplication = application as ExerciseApplication
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        binding.newExerciseButton.setOnClickListener{
            NewItemSheet(null).show(supportFragmentManager, "newItemTag")
            updateDatabase()
        }
        val mainActivity = this
        val exerciseAdapter = ExerciseSetAdapter(exercises, mainActivity)
        lifecycleScope.launch {
            (application as ExerciseApplication).db.exerciseDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    ExerciseSet(
                        entity.exerciseName,
                        entity.reps
                    )
                }.also { mappedList ->
                    exercises.clear()
                    exercises.addAll(mappedList)
                    exerciseAdapter.notifyDataSetChanged()
                }
            }
        }
        setRecyclerView()
    }
        /*
                val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.menu_stats -> setCurrentFragment(StatsFragment())
                        R.id.menu_foods -> setCurrentFragment(FoodFragment())
                    }
                    true
                }
                recyclerView = findViewById(R.id.exercises)
                val mainActivity = activity as MainActivity
                val exerciseAdapter = ExerciseSetAdapter(exercises, mainActivity)
                itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
                itemViewModel.exerciseSetLiveData.observe(viewLifecycleOwner) { exerciseSets ->
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = ExerciseSetAdapter(exerciseSets, mainActivity)
                }
                binding.newFoodButton.setOnClickListener {
                    NewItemSheet(null).show(supportFragmentManager, "newItemTag")

                    // Perform database operations (for example, clearing and inserting data)
                    setCurrentFragment(ExerciseFragment())

                }*/



    /*private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }*/


    // Function to perform database operations
    private fun updateDatabase() {
        val exerciseDao = (application as ExerciseApplication).db.exerciseDao()

        // Perform database operations on a background thread
        lifecycleScope.launch(Dispatchers.IO) {
            // Delete all entries from the database
            exerciseDao.deleteAll()

        }

    }


    private fun insertNewItemToDatabase(newItem: ExerciseSet) {
        val exerciseEntry = ExerciseEntry(exerciseName = newItem.exerciseName, reps = newItem.reps)
        val exerciseDao = (application as ExerciseApplication).db.exerciseDao()

        // Insert the new entry into the database
        exerciseDao.insert(exerciseEntry)
    }
    private fun setRecyclerView() {
        val mainActivity = this
        itemViewModel.exerciseSetLiveData.observe(this) { exerciseItems ->
            binding.exercises.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = ExerciseSetAdapter(exerciseItems, mainActivity)
            }
        }
    }

}