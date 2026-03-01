package com.example.lean.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun todayDateKey(now: Long = System.currentTimeMillis()): String {
    return SimpleDateFormat("yyyyMMdd", Locale.US).format(Date(now))
}
