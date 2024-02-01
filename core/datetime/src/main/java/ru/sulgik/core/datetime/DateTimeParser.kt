package ru.sulgik.core.datetime

import java.time.LocalDate
import java.time.LocalTime

interface DateTimeParser {

    fun parseDate(value: String): LocalDate

    fun parseTime(value: String): LocalTime

    fun formatTime(time: LocalTime): String

    fun formatDateWithWeek(time: LocalDate): String

}