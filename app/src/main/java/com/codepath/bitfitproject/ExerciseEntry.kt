package com.codepath.bitfitproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class ExerciseEntry (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "exerciseName") val exerciseName: String?,
    @ColumnInfo(name = "reps") val reps: Int?
)