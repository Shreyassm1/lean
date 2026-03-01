package com.example.lean.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun toTimeLabel(minutes: Int): String {
    val hrs = minutes / 60
    val mins = minutes % 60
    val suffix = if (hrs >= 12) "PM" else "AM"
    val displayHour = when (val h = hrs % 12) {
        0 -> 12
        else -> h
    }
    return String.format(Locale.getDefault(), "%d:%02d %s", displayHour, mins, suffix)
}

fun formatDateTime(timestamp: Long): String {
    return SimpleDateFormat("MMM d, h:mm a", Locale.getDefault()).format(Date(timestamp))
}
