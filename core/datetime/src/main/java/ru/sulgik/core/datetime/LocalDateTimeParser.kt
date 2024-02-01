package ru.sulgik.core.datetime

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDateTimeParser = staticCompositionLocalOf<DateTimeParser> { error("LocalDateTimeParser does not provided") }