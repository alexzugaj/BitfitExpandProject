package com.codepath.bitfitproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application){
    // Use LiveData with a List type
    val exerciseSetLiveData = MutableLiveData<List<ExerciseSet>>()
    private val exerciseDao: ExerciseDao = ExerciseDatabase.getInstance(application).exerciseDao()

    init {
        exerciseSetLiveData.value = mutableListOf()
    }

    fun addWishItem(newItem: ExerciseSet) {
        val list = exerciseSetLiveData.value?.toMutableList() ?: mutableListOf()
        list.add(newItem)
        exerciseSetLiveData.postValue(list)
        // Print/log the contents of foodItemsLiveData
        list.forEach { item ->
            println("Added Exercise: ${item.exerciseName}, Reps: ${item.reps}")
        }
        insertNewItemToDatabase(newItem)

    }
    fun insertNewItemToDatabase(newItem: ExerciseSet) {
        val exerciseEntry = ExerciseEntry(exerciseName = newItem.exerciseName, reps = newItem.reps)

        // Use a coroutine to perform the database insertion on a background thread
        GlobalScope.launch(Dispatchers.IO) {
            val setEntryDao = (getApplication() as ExerciseApplication).db.exerciseDao()
            setEntryDao.insertAll(listOf(exerciseEntry))
        }
    }
}