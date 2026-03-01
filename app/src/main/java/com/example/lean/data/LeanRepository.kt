package com.example.lean.data

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class LeanRepository(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun isSetupDone(): Boolean = prefs.getBoolean(KEY_SETUP_DONE, false)

    fun setSetupDone(done: Boolean) {
        prefs.edit().putBoolean(KEY_SETUP_DONE, done).apply()
    }

    fun getCalorieGoal(): Int = prefs.getInt(KEY_CALORIE_GOAL, 2000)

    fun setCalorieGoal(goal: Int) {
        prefs.edit().putInt(KEY_CALORIE_GOAL, goal).apply()
    }

    fun getWaterGoal(): Int = prefs.getInt(KEY_WATER_GOAL, 2500)

    fun setWaterGoal(goal: Int) {
        prefs.edit().putInt(KEY_WATER_GOAL, goal).apply()
    }

    fun loadTimetable(): List<TimetableEntry> {
        val raw = prefs.getString(KEY_TIMETABLE, "[]") ?: "[]"
        val arr = JSONArray(raw)
        return buildList {
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                add(
                    TimetableEntry(
                        id = obj.getLong("id"),
                        title = obj.getString("title"),
                        timeMinutes = obj.getInt("timeMinutes")
                    )
                )
            }
        }
    }

    fun saveTimetable(items: List<TimetableEntry>) {
        val arr = JSONArray()
        items.forEach { entry ->
            arr.put(
                JSONObject()
                    .put("id", entry.id)
                    .put("title", entry.title)
                    .put("timeMinutes", entry.timeMinutes)
            )
        }
        prefs.edit().putString(KEY_TIMETABLE, arr.toString()).apply()
    }

    fun loadCalorieLogs(): List<CalorieLog> {
        val raw = prefs.getString(KEY_CALORIE_LOGS, "[]") ?: "[]"
        val arr = JSONArray(raw)
        return buildList {
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                add(
                    CalorieLog(
                        id = obj.getLong("id"),
                        name = obj.getString("name"),
                        calories = obj.getInt("calories"),
                        proteinGrams = obj.optDouble("proteinGrams", 0.0),
                        carbsGrams = obj.optDouble("carbsGrams", 0.0),
                        fatsGrams = obj.optDouble("fatsGrams", 0.0),
                        timestamp = obj.getLong("timestamp")
                    )
                )
            }
        }
    }

    fun saveCalorieLogs(items: List<CalorieLog>) {
        val arr = JSONArray()
        items.forEach { log ->
            arr.put(
                JSONObject()
                    .put("id", log.id)
                    .put("name", log.name)
                    .put("calories", log.calories)
                    .put("proteinGrams", log.proteinGrams)
                    .put("carbsGrams", log.carbsGrams)
                    .put("fatsGrams", log.fatsGrams)
                    .put("timestamp", log.timestamp)
            )
        }
        prefs.edit().putString(KEY_CALORIE_LOGS, arr.toString()).apply()
    }

    fun loadWaterLogs(): List<WaterLog> {
        val raw = prefs.getString(KEY_WATER_LOGS, "[]") ?: "[]"
        val arr = JSONArray(raw)
        return buildList {
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                add(
                    WaterLog(
                        id = obj.getLong("id"),
                        milliliters = obj.getInt("milliliters"),
                        timestamp = obj.getLong("timestamp")
                    )
                )
            }
        }
    }

    fun saveWaterLogs(items: List<WaterLog>) {
        val arr = JSONArray()
        items.forEach { log ->
            arr.put(
                JSONObject()
                    .put("id", log.id)
                    .put("milliliters", log.milliliters)
                    .put("timestamp", log.timestamp)
            )
        }
        prefs.edit().putString(KEY_WATER_LOGS, arr.toString()).apply()
    }

    fun loadWorkoutLogs(): List<WorkoutLog> {
        val raw = prefs.getString(KEY_WORKOUT_LOGS, "[]") ?: "[]"
        val arr = JSONArray(raw)
        return buildList {
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                add(
                    WorkoutLog(
                        id = obj.getLong("id"),
                        routineId = obj.getLong("routineId"),
                        routineName = obj.getString("routineName"),
                        minutes = obj.getInt("minutes"),
                        timestamp = obj.getLong("timestamp"),
                        source = obj.optString("source", "manual")
                    )
                )
            }
        }
    }

    fun saveWorkoutLogs(items: List<WorkoutLog>) {
        val arr = JSONArray()
        items.forEach { log ->
            arr.put(
                JSONObject()
                    .put("id", log.id)
                    .put("routineId", log.routineId)
                    .put("routineName", log.routineName)
                    .put("minutes", log.minutes)
                    .put("timestamp", log.timestamp)
                    .put("source", log.source)
            )
        }
        prefs.edit().putString(KEY_WORKOUT_LOGS, arr.toString()).apply()
    }

    fun loadWorkoutTasksForToday(dateKey: String): List<WorkoutTask> {
        val storedDate = prefs.getString(KEY_WORKOUT_TASKS_DATE, "") ?: ""
        if (storedDate != dateKey) {
            prefs.edit()
                .putString(KEY_WORKOUT_TASKS_DATE, dateKey)
                .putString(KEY_WORKOUT_TASKS, "[]")
                .apply()
            return emptyList()
        }

        val raw = prefs.getString(KEY_WORKOUT_TASKS, "[]") ?: "[]"
        val arr = JSONArray(raw)
        return buildList {
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                add(
                    WorkoutTask(
                        id = obj.getLong("id"),
                        section = obj.getString("section"),
                        title = obj.getString("title"),
                        done = obj.optBoolean("done", false)
                    )
                )
            }
        }
    }

    fun saveWorkoutTasksForToday(dateKey: String, tasks: List<WorkoutTask>) {
        val arr = JSONArray()
        tasks.forEach { task ->
            arr.put(
                JSONObject()
                    .put("id", task.id)
                    .put("section", task.section)
                    .put("title", task.title)
                    .put("done", task.done)
            )
        }
        prefs.edit()
            .putString(KEY_WORKOUT_TASKS_DATE, dateKey)
            .putString(KEY_WORKOUT_TASKS, arr.toString())
            .apply()
    }

    fun loadTimetableCompletion(dateKey: String): Set<Long> {
        val raw = prefs.getString("$KEY_TIMETABLE_COMPLETION_PREFIX$dateKey", "[]") ?: "[]"
        val arr = JSONArray(raw)
        return buildSet {
            for (i in 0 until arr.length()) {
                add(arr.getLong(i))
            }
        }
    }

    fun saveTimetableCompletion(dateKey: String, ids: Set<Long>) {
        val arr = JSONArray()
        ids.forEach { arr.put(it) }
        prefs.edit().putString("$KEY_TIMETABLE_COMPLETION_PREFIX$dateKey", arr.toString()).apply()
    }

    companion object {
        private const val PREFS_NAME = "lean_repository"
        private const val KEY_SETUP_DONE = "setup_done"
        private const val KEY_CALORIE_GOAL = "calorie_goal"
        private const val KEY_WATER_GOAL = "water_goal"
        private const val KEY_TIMETABLE = "timetable"
        private const val KEY_CALORIE_LOGS = "calorie_logs"
        private const val KEY_WATER_LOGS = "water_logs"
        private const val KEY_WORKOUT_LOGS = "workout_logs"
        private const val KEY_WORKOUT_TASKS = "workout_tasks"
        private const val KEY_WORKOUT_TASKS_DATE = "workout_tasks_date"
        private const val KEY_TIMETABLE_COMPLETION_PREFIX = "timetable_completion_"
    }
}
