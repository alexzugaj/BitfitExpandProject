package com.codepath.bitfitproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
//DONE
@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise_table")
    fun getAll(): Flow<List<ExerciseEntry>>
    @Insert
    fun insertAll(exercises: List<ExerciseEntry>)
    @Insert
    fun insert(exercise: ExerciseEntry)
    @Query("DELETE FROM exercise_table")
    fun deleteAll()

    @Query("SELECT SUM(reps) FROM exercise_table")
    fun getSumReps(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM exercise_table")
    fun getCountExercises(): LiveData<Int>

    @Query("SELECT AVG(reps) FROM exercise_table")
    fun getAvgReps(): LiveData<Float>

    @Query("SELECT MIN(reps) FROM exercise_table")
    fun getMinReps(): LiveData<Int>

    @Query("SELECT MAX(reps) FROM exercise_table")
    fun getMaxReps(): LiveData<Int>
}