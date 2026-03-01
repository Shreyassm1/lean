package com.example.lean

import android.app.TimePickerDialog
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
import com.example.lean.data.TimetableEntry
import com.example.lean.navigation.BaseNavActivity
import com.example.lean.ui.TimetableAdapter
import com.example.lean.util.todayDateKey
import com.example.lean.util.toTimeLabel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TimetableActivity : BaseNavActivity() {

    private lateinit var repository: LeanRepository
    private lateinit var adapter: TimetableAdapter
    private lateinit var tvTimetableEmpty: TextView
    private lateinit var tvTimetableProgress: TextView

    private var timetableEntries = mutableListOf<TimetableEntry>()
    private var completedTodayIds = mutableSetOf<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_timetable)
        val bottomNav: com.google.android.material.bottomnavigation.BottomNavigationView =
            findViewById(R.id.bottomNav)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            bottomNav.updatePadding(bottom = systemBars.bottom)
            insets
        }

        repository = LeanRepository(this)
        tvTimetableEmpty = findViewById(R.id.tvTimetableEmpty)
        tvTimetableProgress = findViewById(R.id.tvTimetableProgress)

        adapter = TimetableAdapter(
            onEdit = { showTimetableDialog(it) },
            onDelete = { entry ->
                timetableEntries = timetableEntries.filterNot { it.id == entry.id }.toMutableList()
                completedTodayIds.remove(entry.id)
                repository.saveTimetable(timetableEntries)
                repository.saveTimetableCompletion(todayDateKey(), completedTodayIds)
                renderTimetable()
            },
            onDoneToggled = { entry, isDone ->
                if (isDone) {
                    completedTodayIds.add(entry.id)
                } else {
                    completedTodayIds.remove(entry.id)
                }
                repository.saveTimetableCompletion(todayDateKey(), completedTodayIds)
                renderTimetable()
            }
        )

        findViewById<RecyclerView>(R.id.rvTimetable).apply {
            layoutManager = LinearLayoutManager(this@TimetableActivity)
            adapter = this@TimetableActivity.adapter
        }

        findViewById<Button>(R.id.btnAddTimetable).setOnClickListener { showTimetableDialog() }
        findViewById<Button>(R.id.btnClearTimetable).setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Clear timetable?")
                .setMessage("This removes all timetable entries.")
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.clear_all) { _, _ ->
                    timetableEntries.clear()
                    completedTodayIds.clear()
                    repository.saveTimetable(timetableEntries)
                    repository.saveTimetableCompletion(todayDateKey(), completedTodayIds)
                    renderTimetable()
                }
                .show()
        }
        findViewById<Button>(R.id.btnResetToday).setOnClickListener {
            completedTodayIds.clear()
            repository.saveTimetableCompletion(todayDateKey(), completedTodayIds)
            renderTimetable()
        }

        setupBottomNav(R.id.nav_timetable)
    }

    override fun onResume() {
        super.onResume()
        timetableEntries = repository.loadTimetable().toMutableList()
        completedTodayIds = repository.loadTimetableCompletion(todayDateKey()).toMutableSet()
        completedTodayIds = completedTodayIds
            .filter { id -> timetableEntries.any { it.id == id } }
            .toMutableSet()
        renderTimetable()
    }

    private fun renderTimetable() {
        adapter.submitList(timetableEntries, completedTodayIds)
        tvTimetableProgress.text =
            "${completedTodayIds.size}/${timetableEntries.size} done today"
        tvTimetableEmpty.visibility = if (timetableEntries.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun showTimetableDialog(existing: TimetableEntry? = null) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_timetable_entry, null)
        val etTitle = dialogView.findViewById<EditText>(R.id.etTitle)
        val btnTime = dialogView.findViewById<Button>(R.id.btnTime)

        var selectedMinutes = existing?.timeMinutes ?: (8 * 60)
        etTitle.setText(existing?.title.orEmpty())
        btnTime.text = "Time: ${toTimeLabel(selectedMinutes)}"

        btnTime.setOnClickListener {
            val currentHour = selectedMinutes / 60
            val currentMinute = selectedMinutes % 60
            TimePickerDialog(this, { _, hourOfDay, minute ->
                selectedMinutes = hourOfDay * 60 + minute
                btnTime.text = "Time: ${toTimeLabel(selectedMinutes)}"
            }, currentHour, currentMinute, false).show()
        }

        MaterialAlertDialogBuilder(this)
            .setTitle(if (existing == null) "Add timetable entry" else "Edit timetable entry")
            .setView(dialogView)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.save, null)
            .create()
            .also { dialog ->
                dialog.setOnShowListener {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        val title = etTitle.text?.toString()?.trim().orEmpty()
                        if (title.isBlank()) {
                            etTitle.error = "Required"
                            return@setOnClickListener
                        }

                        val id = existing?.id ?: System.currentTimeMillis()
                        val updated = TimetableEntry(
                            id = id,
                            title = title,
                            timeMinutes = selectedMinutes
                        )

                        timetableEntries = timetableEntries
                            .filterNot { it.id == id }
                            .plus(updated)
                            .toMutableList()
                        completedTodayIds.remove(id)

                        repository.saveTimetable(timetableEntries)
                        repository.saveTimetableCompletion(todayDateKey(), completedTodayIds)
                        renderTimetable()
                        dialog.dismiss()
                    }
                }
                dialog.show()
            }
    }

}
