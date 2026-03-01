package com.example.lean

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lean.data.LeanRepository
import com.example.lean.data.WorkoutTask
import com.example.lean.navigation.BaseNavActivity
import com.example.lean.ui.WorkoutTaskAdapter
import com.example.lean.util.todayDateKey
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WorkoutActivity : BaseNavActivity() {

    private lateinit var repository: LeanRepository
    private val todayKey: String
        get() = todayDateKey()

    private var tasks = mutableListOf<WorkoutTask>()

    private lateinit var morningAdapter: WorkoutTaskAdapter
    private lateinit var eveningAdapter: WorkoutTaskAdapter

    private lateinit var tvMorningEmpty: TextView
    private lateinit var tvEveningEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_workout)
        val bottomNav: com.google.android.material.bottomnavigation.BottomNavigationView =
            findViewById(R.id.bottomNav)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            bottomNav.updatePadding(bottom = systemBars.bottom)
            insets
        }

        repository = LeanRepository(this)

        tvMorningEmpty = findViewById(R.id.tvMorningEmpty)
        tvEveningEmpty = findViewById(R.id.tvEveningEmpty)

        morningAdapter = createAdapter()
        eveningAdapter = createAdapter()

        findViewById<RecyclerView>(R.id.rvMorningTasks).apply {
            layoutManager = LinearLayoutManager(this@WorkoutActivity)
            adapter = morningAdapter
        }
        findViewById<RecyclerView>(R.id.rvEveningTasks).apply {
            layoutManager = LinearLayoutManager(this@WorkoutActivity)
            adapter = eveningAdapter
        }

        findViewById<Button>(R.id.btnAddMorningTask).setOnClickListener {
            showAddTaskDialog(SECTION_MORNING)
        }
        findViewById<Button>(R.id.btnAddEveningTask).setOnClickListener {
            showAddTaskDialog(SECTION_EVENING)
        }

        setupBottomNav(R.id.nav_workout)
    }

    override fun onResume() {
        super.onResume()
        tasks = repository.loadWorkoutTasksForToday(todayKey)
            .filter { it.section == SECTION_MORNING || it.section == SECTION_EVENING }
            .toMutableList()
        repository.saveWorkoutTasksForToday(todayKey, tasks)
        render()
    }

    private fun createAdapter(): WorkoutTaskAdapter {
        return WorkoutTaskAdapter(
            onCheckedChanged = { task, done ->
                tasks = tasks.map {
                    if (it.id == task.id) it.copy(done = done) else it
                }.toMutableList()
                persistAndRender()
            },
            onDelete = { task ->
                tasks = tasks.filterNot { it.id == task.id }.toMutableList()
                persistAndRender()
            }
        )
    }

    private fun showAddTaskDialog(section: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_single_input, null)
        val etInput = dialogView.findViewById<EditText>(R.id.etInput)
        etInput.hint = "Task"

        MaterialAlertDialogBuilder(this)
            .setTitle("Add task to $section")
            .setView(dialogView)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.save, null)
            .create()
            .also { dialog ->
                dialog.setOnShowListener {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        val title = etInput.text?.toString()?.trim().orEmpty()
                        if (title.isBlank()) {
                            etInput.error = "Required"
                            return@setOnClickListener
                        }

                        tasks = tasks.plus(
                            WorkoutTask(
                                id = System.currentTimeMillis(),
                                section = section,
                                title = title,
                                done = false
                            )
                        ).toMutableList()

                        persistAndRender()
                        dialog.dismiss()
                    }
                }
                dialog.show()
            }
    }

    private fun persistAndRender() {
        repository.saveWorkoutTasksForToday(todayKey, tasks)
        render()
    }

    private fun render() {
        val morning = tasks.filter { it.section == SECTION_MORNING }
        val evening = tasks.filter { it.section == SECTION_EVENING }

        morningAdapter.submitList(morning)
        eveningAdapter.submitList(evening)

        tvMorningEmpty.visibility = if (morning.isEmpty()) View.VISIBLE else View.GONE
        tvEveningEmpty.visibility = if (evening.isEmpty()) View.VISIBLE else View.GONE
    }

    companion object {
        private const val SECTION_MORNING = "Morning"
        private const val SECTION_EVENING = "Evening"
    }
}
