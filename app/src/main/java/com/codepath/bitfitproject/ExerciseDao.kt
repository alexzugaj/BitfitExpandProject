package com.codepath.bitfitproject

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
    @Query("DELETE FROM exercise_table")
    fun deleteAll()
}