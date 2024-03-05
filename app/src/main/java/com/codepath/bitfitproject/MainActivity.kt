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
        val exerciseFragment: Fragment = ExercisesFragment()
        val statsFragment: Fragment = StatisticsFragment()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.exercises -> fragment = exerciseFragment
                R.id.statistics -> fragment = statsFragment
            }
            replaceFragment(fragment)
            true
        }

        bottomNavigationView.selectedItemId = R.id.exercises
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        binding.newExerciseButton.setOnClickListener{
            NewItemSheet(null).show(supportFragmentManager, "newItemTag")
            replaceFragment(exerciseFragment)
        }

        //setRecyclerView()
    }
    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


    /*private fun setRecyclerView() {
        val mainActivity = this
        itemViewModel.exerciseSetLiveData.observe(this) { exerciseItems ->
            binding.exercise_recycler_view.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = ExerciseSetAdapter(exerciseItems, mainActivity)
            }
        }
    }*/

}