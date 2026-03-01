package com.example.lean

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.lean.data.LeanRepository
import com.example.lean.navigation.BaseNavActivity
import com.example.lean.util.todayDateKey
import com.google.android.material.progressindicator.LinearProgressIndicator

class MainActivity : BaseNavActivity() {

    private lateinit var repository: LeanRepository
    private lateinit var tvHeroTimetable: TextView
    private lateinit var tvHeroCalories: TextView
    private lateinit var tvHeroWorkout: TextView
    private lateinit var tvOverviewTimetable: TextView
    private lateinit var tvOverviewCalories: TextView
    private lateinit var tvOverviewWorkout: TextView
    private lateinit var progressOverviewCalories: LinearProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val bottomNav: com.google.android.material.bottomnavigation.BottomNavigationView =
            findViewById(R.id.bottomNav)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            bottomNav.updatePadding(bottom = systemBars.bottom)
            insets
        }

        repository = LeanRepository(this)

        tvHeroTimetable = findViewById(R.id.tvHeroTimetable)
        tvHeroCalories = findViewById(R.id.tvHeroCalories)
        tvHeroWorkout = findViewById(R.id.tvHeroWorkout)
        tvOverviewTimetable = findViewById(R.id.tvOverviewTimetable)
        tvOverviewCalories = findViewById(R.id.tvOverviewCalories)
        tvOverviewWorkout = findViewById(R.id.tvOverviewWorkout)
        progressOverviewCalories = findViewById(R.id.progressOverviewCalories)

        setupBottomNav(R.id.nav_overview)
    }

    override fun onResume() {
        super.onResume()
        renderOverview()
    }

    private fun renderOverview() {
        val timetableEntries = repository.loadTimetable()
        val calorieLogs = repository.loadCalorieLogs()
        val workoutTasks = repository.loadWorkoutTasksForToday(todayDateKey())

        val completedToday = repository.loadTimetableCompletion(todayDateKey())
            .count { id -> timetableEntries.any { it.id == id } }
        val goal = repository.getCalorieGoal().coerceAtLeast(1)
        val totalCalories = calorieLogs.sumOf { it.calories }
        val caloriePercent = ((totalCalories * 100f) / goal).toInt().coerceIn(0, 100)

        val doneWorkoutTasks = workoutTasks.count { it.done }

        tvHeroTimetable.text = "${completedToday}/${timetableEntries.size} done"
        tvHeroCalories.text = "$totalCalories kcal"
        tvHeroWorkout.text = "${doneWorkoutTasks}/${workoutTasks.size}"

        tvOverviewTimetable.text =
            "${timetableEntries.size} entries, $completedToday done today"
        tvOverviewCalories.text = "$totalCalories / $goal kcal ($caloriePercent%)"
        tvOverviewWorkout.text =
            "${doneWorkoutTasks}/${workoutTasks.size} workout tasks completed today"

        progressOverviewCalories.progress = caloriePercent
    }
}
