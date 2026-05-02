package com.ups.android.apps.common.model

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Number.formatAsString(): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.minimumFractionDigits = 1
    return format.format(this@formatAsString)
}


enum class DateFormatPattern(val pattern: String) {
    SHORT("MMM, yy"),
    LONG("dd MMM, yyyy"),
}

fun Long.toDateFormat(format: DateFormatPattern): String {
    val date = Date(this@toDateFormat)
    val formatter = SimpleDateFormat(format.pattern, Locale.US)
    return formatter.format(this@toDateFormat)
}

fun now() = System.currentTimeMillis()