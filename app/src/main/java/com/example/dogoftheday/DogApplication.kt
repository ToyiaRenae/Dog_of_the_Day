package com.example.dogoftheday

import android.app.Application
import com.example.dogoftheday.database.AppDatabase

class DogApplication :Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this
    ) }
}