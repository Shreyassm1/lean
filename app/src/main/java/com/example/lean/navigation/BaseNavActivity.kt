package com.example.lean.navigation

import android.content.Intent
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.example.lean.CaloriesActivity
import com.example.lean.MainActivity
import com.example.lean.R
import com.example.lean.TimetableActivity
import com.example.lean.WorkoutActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseNavActivity : AppCompatActivity() {

    protected fun setupBottomNav(@IdRes selectedItemId: Int) {
        val nav = findViewById<BottomNavigationView>(R.id.bottomNav)
        nav.selectedItemId = selectedItemId
        nav.setOnItemSelectedListener { item ->
            if (item.itemId == selectedItemId) return@setOnItemSelectedListener true

            val target = when (item.itemId) {
                R.id.nav_overview -> MainActivity::class.java
                R.id.nav_timetable -> TimetableActivity::class.java
                R.id.nav_calories -> CaloriesActivity::class.java
                R.id.nav_workout -> WorkoutActivity::class.java
                else -> return@setOnItemSelectedListener false
            }

            startActivity(Intent(this, target))
            overridePendingTransition(0, 0)
            finish()
            true
        }
    }
}
