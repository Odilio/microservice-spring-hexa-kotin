package com.arquitetura.hexagonal.application.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "yyyy-MM-dd"): String {
    return SimpleDateFormat(pattern).format(this)
}

fun String.convertStringToDate(pattern: String = "yyyy-MM-dd"): Date? {
    return try {
        SimpleDateFormat(pattern).parse(this)
    } catch (e: Throwable) {
        null
    }
}
