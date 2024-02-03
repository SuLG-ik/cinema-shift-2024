package ru.sulgik.core.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

interface DateTimeFormatter {

    fun parseDate(value: String): LocalDate

    fun parseTime(value: String): LocalTime

    fun formatTime(time: LocalTime): String

    fun formatDateWithWeek(time: LocalDate): String

    fun formatDateWithTime(date: LocalDate, time: LocalTime): String

}