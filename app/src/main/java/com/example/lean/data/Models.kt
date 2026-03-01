package com.example.lean.data

data class TimetableEntry(
    val id: Long,
    val title: String,
    val timeMinutes: Int
)

data class CalorieLog(
    val id: Long,
    val name: String,
    val calories: Int,
    val proteinGrams: Double,
    val carbsGrams: Double,
    val fatsGrams: Double,
    val timestamp: Long
)

data class WaterLog(
    val id: Long,
    val milliliters: Int,
    val timestamp: Long
)

data class WorkoutLog(
    val id: Long,
    val routineId: Long,
    val routineName: String,
    val minutes: Int,
    val timestamp: Long,
    val source: String
)

data class WorkoutTask(
    val id: Long,
    val section: String,
    val title: String,
    val done: Boolean
)
