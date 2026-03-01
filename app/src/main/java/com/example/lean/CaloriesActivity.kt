package com.example.lean

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lean.data.CalorieLog
import com.example.lean.data.LeanRepository
import com.example.lean.navigation.BaseNavActivity
import com.example.lean.ui.CalorieLogAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.LinearProgressIndicator
import java.util.Locale
import kotlin.math.round

class CaloriesActivity : BaseNavActivity() {

    private lateinit var repository: LeanRepository
    private lateinit var adapter: CalorieLogAdapter

    private lateinit var tvCalorieGoal: TextView
    private lateinit var tvCaloriesProgress: TextView
    private lateinit var progressCalories: LinearProgressIndicator
    private lateinit var tvCaloriesEmpty: TextView

    private var calorieGoal = 2000
    private var calorieLogs = mutableListOf<CalorieLog>()
    private val predefinedFoods = listOf(
        PredefinedFood("boiled_egg", "Boiled egg", 78, 6.3, 0.6, 5.3, "1 large egg"),
        PredefinedFood("roti", "Roti", 80, 2.4, 15.0, 1.8, "1 small roti"),
        PredefinedFood("fried_rice", "Fried rice", 168, 3.5, 24.0, 3.8, "100 g serving"),
        PredefinedFood("cup_coffee", "Cup of coffee", 95, 3.0, 13.0, 3.2, "1 cup (with milk + sugar)"),
        PredefinedFood("banana", "Banana", 105, 1.3, 27.0, 0.3, "1 medium banana")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calories)
        val bottomNav: com.google.android.material.bottomnavigation.BottomNavigationView =
            findViewById(R.id.bottomNav)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            bottomNav.updatePadding(bottom = systemBars.bottom)
            insets
        }

        repository = LeanRepository(this)

        tvCalorieGoal = findViewById(R.id.tvCalorieGoal)
        tvCaloriesProgress = findViewById(R.id.tvCaloriesProgress)
        progressCalories = findViewById(R.id.progressCalories)
        tvCaloriesEmpty = findViewById(R.id.tvCaloriesEmpty)

        adapter = CalorieLogAdapter { log ->
            calorieLogs = calorieLogs.filterNot { it.id == log.id }.toMutableList()
            repository.saveCalorieLogs(calorieLogs)
            renderCalories()
        }

        findViewById<RecyclerView>(R.id.rvCalorieLogs).apply {
            layoutManager = LinearLayoutManager(this@CaloriesActivity)
            adapter = this@CaloriesActivity.adapter
        }

        findViewById<Button>(R.id.btnSetCalorieGoal).setOnClickListener {
            showCalorieGoalDialog(forceSetup = false)
        }
        findViewById<Button>(R.id.btnAddCalorieLog).setOnClickListener {
            showCalorieLogDialog()
        }
        findViewById<Button>(R.id.btnClearCalorieLogs).setOnClickListener {
            calorieLogs.clear()
            repository.saveCalorieLogs(calorieLogs)
            renderCalories()
        }

        setupBottomNav(R.id.nav_calories)
    }

    override fun onResume() {
        super.onResume()
        calorieGoal = repository.getCalorieGoal()
        calorieLogs = repository.loadCalorieLogs().toMutableList()
        renderCalories()

        if (!repository.isSetupDone()) {
            showCalorieGoalDialog(forceSetup = true)
        }
    }

    private fun renderCalories() {
        adapter.submitList(calorieLogs)
        tvCaloriesEmpty.visibility = if (calorieLogs.isEmpty()) View.VISIBLE else View.GONE

        val total = calorieLogs.sumOf { it.calories }
        val totalProtein = calorieLogs.sumOf { it.proteinGrams }
        val totalCarbs = calorieLogs.sumOf { it.carbsGrams }
        val totalFats = calorieLogs.sumOf { it.fatsGrams }
        val goal = calorieGoal.coerceAtLeast(1)
        val percent = ((total.toFloat() / goal.toFloat()) * 100f).toInt().coerceIn(0, 100)

        tvCalorieGoal.text = "Goal: $calorieGoal kcal"
        tvCaloriesProgress.text =
            "$total / $calorieGoal kcal\nP ${formatGrams(totalProtein)}g  •  C ${formatGrams(totalCarbs)}g  •  F ${formatGrams(totalFats)}g"
        progressCalories.progress = percent
    }

    private fun showCalorieGoalDialog(forceSetup: Boolean) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_single_input, null)
        val etInput = dialogView.findViewById<EditText>(R.id.etInput)
        etInput.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        etInput.setText(calorieGoal.toString())

        val builder = MaterialAlertDialogBuilder(this)
            .setTitle(if (forceSetup) "Set your daily calorie goal" else "Update calorie goal")
            .setMessage("This goal drives your progress bar.")
            .setView(dialogView)
            .setPositiveButton(R.string.save, null)

        if (!forceSetup) {
            builder.setNegativeButton(R.string.cancel, null)
        }

        val dialog = builder.create()
        if (forceSetup) {
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
        }

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val goal = etInput.text?.toString()?.toIntOrNull()
                if (goal == null || goal <= 0) {
                    etInput.error = "Enter a valid number"
                    return@setOnClickListener
                }

                calorieGoal = goal
                repository.setCalorieGoal(goal)
                if (forceSetup) {
                    repository.setSetupDone(true)
                }
                renderCalories()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun showCalorieLogDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_calorie_log, null)
        val containerFoods = dialogView.findViewById<LinearLayout>(R.id.containerFoods)
        val tvSelectedTotal = dialogView.findViewById<TextView>(R.id.tvSelectedTotal)
        val tvSelectedMacros = dialogView.findViewById<TextView>(R.id.tvSelectedMacros)
        val tvValidation = dialogView.findViewById<TextView>(R.id.tvValidation)
        val btnCustomIntake = dialogView.findViewById<Button>(R.id.btnCustomIntake)
        val selectedCounts = mutableMapOf<String, Int>()

        fun updateTotal() {
            val totalCalories = predefinedFoods.sumOf { food ->
                (selectedCounts[food.key] ?: 0) * food.caloriesPerUnit
            }
            val totalProtein = predefinedFoods.sumOf { food ->
                (selectedCounts[food.key] ?: 0) * food.proteinGrams
            }
            val totalCarbs = predefinedFoods.sumOf { food ->
                (selectedCounts[food.key] ?: 0) * food.carbsGrams
            }
            val totalFats = predefinedFoods.sumOf { food ->
                (selectedCounts[food.key] ?: 0) * food.fatsGrams
            }

            tvSelectedTotal.text = "Calories: $totalCalories kcal"
            tvSelectedMacros.text =
                "Protein ${formatGrams(totalProtein)} g  •  Carbs ${formatGrams(totalCarbs)} g  •  Fats ${formatGrams(totalFats)} g"
        }

        predefinedFoods.forEach { food ->
            val row = layoutInflater.inflate(R.layout.item_food_quantity, containerFoods, false)
            val tvFoodName = row.findViewById<TextView>(R.id.tvFoodName)
            val tvFoodMeta = row.findViewById<TextView>(R.id.tvFoodMeta)
            val tvQty = row.findViewById<TextView>(R.id.tvQty)
            val btnMinus = row.findViewById<Button>(R.id.btnMinus)
            val btnPlus = row.findViewById<Button>(R.id.btnPlus)

            tvFoodName.text = food.name
            tvFoodMeta.text =
                "${food.unitLabel} • ${food.caloriesPerUnit} kcal • P ${formatGrams(food.proteinGrams)}g • C ${formatGrams(food.carbsGrams)}g • F ${formatGrams(food.fatsGrams)}g"
            selectedCounts[food.key] = 0
            tvQty.text = "0"

            btnMinus.setOnClickListener {
                val current = selectedCounts[food.key] ?: 0
                val next = (current - 1).coerceAtLeast(0)
                selectedCounts[food.key] = next
                tvQty.text = next.toString()
                tvValidation.visibility = View.GONE
                updateTotal()
            }

            btnPlus.setOnClickListener {
                val current = selectedCounts[food.key] ?: 0
                val next = current + 1
                selectedCounts[food.key] = next
                tvQty.text = next.toString()
                tvValidation.visibility = View.GONE
                updateTotal()
            }

            containerFoods.addView(row)
        }

        updateTotal()

        MaterialAlertDialogBuilder(this)
            .setTitle("Add calorie intake")
            .setView(dialogView)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.add_calorie_log, null)
            .create()
            .also { dialog ->
                btnCustomIntake.setOnClickListener {
                    dialog.dismiss()
                    showCustomCalorieLogDialog()
                }

                dialog.setOnShowListener {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        val selectedFoods = predefinedFoods
                            .map { food -> food to (selectedCounts[food.key] ?: 0) }
                            .filter { (_, qty) -> qty > 0 }
                        if (selectedFoods.isEmpty()) {
                            tvValidation.visibility = View.VISIBLE
                            return@setOnClickListener
                        }

                        val now = System.currentTimeMillis()
                        val newLogs = selectedFoods.mapIndexed { index, (food, qty) ->
                            val calories = food.caloriesPerUnit * qty
                            CalorieLog(
                                id = now + index,
                                name = if (qty == 1) {
                                    food.name
                                } else {
                                    "$qty x ${food.name}"
                                },
                                calories = calories,
                                proteinGrams = round1(food.proteinGrams * qty),
                                carbsGrams = round1(food.carbsGrams * qty),
                                fatsGrams = round1(food.fatsGrams * qty),
                                timestamp = now
                            )
                        }

                        calorieLogs = calorieLogs.plus(newLogs).toMutableList()

                        repository.saveCalorieLogs(calorieLogs)
                        renderCalories()
                        dialog.dismiss()
                    }
                }
                dialog.show()
            }
    }

    private fun showCustomCalorieLogDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_calorie_log, null)
        val etName = dialogView.findViewById<EditText>(R.id.etName)
        val etCalories = dialogView.findViewById<EditText>(R.id.etCalories)
        val etProtein = dialogView.findViewById<EditText>(R.id.etProtein)
        val etCarbs = dialogView.findViewById<EditText>(R.id.etCarbs)
        val etFats = dialogView.findViewById<EditText>(R.id.etFats)

        MaterialAlertDialogBuilder(this)
            .setTitle("Custom intake")
            .setView(dialogView)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.save, null)
            .create()
            .also { dialog ->
                dialog.setOnShowListener {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        val name = etName.text?.toString()?.trim().orEmpty()
                        val calories = etCalories.text?.toString()?.toIntOrNull()
                        val protein = etProtein.text?.toString()?.toDoubleOrNull()
                        val carbs = etCarbs.text?.toString()?.toDoubleOrNull()
                        val fats = etFats.text?.toString()?.toDoubleOrNull()

                        if (name.isBlank()) {
                            etName.error = "Required"
                            return@setOnClickListener
                        }
                        if (calories == null || calories < 0) {
                            etCalories.error = "Enter calories"
                            return@setOnClickListener
                        }
                        if (protein == null || protein < 0) {
                            etProtein.error = "Enter protein"
                            return@setOnClickListener
                        }
                        if (carbs == null || carbs < 0) {
                            etCarbs.error = "Enter carbs"
                            return@setOnClickListener
                        }
                        if (fats == null || fats < 0) {
                            etFats.error = "Enter fats"
                            return@setOnClickListener
                        }

                        calorieLogs = calorieLogs.plus(
                            CalorieLog(
                                id = System.currentTimeMillis(),
                                name = name,
                                calories = calories,
                                proteinGrams = round1(protein),
                                carbsGrams = round1(carbs),
                                fatsGrams = round1(fats),
                                timestamp = System.currentTimeMillis()
                            )
                        ).toMutableList()

                        repository.saveCalorieLogs(calorieLogs)
                        renderCalories()
                        dialog.dismiss()
                    }
                }
                dialog.show()
            }
    }

    private fun round1(value: Double): Double {
        return round(value * 10.0) / 10.0
    }

    private fun formatGrams(value: Double): String {
        return String.format(Locale.US, "%.1f", round1(value))
    }

    private data class PredefinedFood(
        val key: String,
        val name: String,
        val caloriesPerUnit: Int,
        val proteinGrams: Double,
        val carbsGrams: Double,
        val fatsGrams: Double,
        val unitLabel: String
    )
}
