package com.example.lean.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lean.R
import com.example.lean.data.WorkoutTask
import com.google.android.material.checkbox.MaterialCheckBox
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial

class WorkoutTaskAdapter(
    private val onCheckedChanged: (WorkoutTask, Boolean) -> Unit,
    private val onDelete: (WorkoutTask) -> Unit
) : RecyclerView.Adapter<WorkoutTaskAdapter.TaskViewHolder>() {

    private val items = mutableListOf<WorkoutTask>()

    fun submitList(tasks: List<WorkoutTask>) {
        items.clear()
        items.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val checkTask: MaterialCheckBox = itemView.findViewById(R.id.checkTask)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(item: WorkoutTask) {
            checkTask.setOnCheckedChangeListener(null)
            checkTask.text = item.title
            checkTask.isChecked = item.done
            checkTask.setOnCheckedChangeListener { _, checked -> onCheckedChanged(item, checked) }

            val iconColor = ColorStateList.valueOf(
                ContextCompat.getColor(itemView.context, R.color.ink_secondary)
            )
            val iconSizePx = (18 * itemView.resources.displayMetrics.density).toInt()
            btnDelete.setImageDrawable(
                IconicsDrawable(itemView.context, CommunityMaterial.Icon3.cmd_trash_can_outline).apply {
                    colorList = iconColor
                    sizeXPx = iconSizePx
                    sizeYPx = iconSizePx
                }
            )
            btnDelete.setOnClickListener { onDelete(item) }
        }
    }
}
