package com.codepath.bitfitproject

import android.app.Application
//DONE
class ExerciseApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}