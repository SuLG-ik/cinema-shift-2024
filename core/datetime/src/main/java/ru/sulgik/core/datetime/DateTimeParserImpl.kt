package ru.sulgik.core.datetime

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DateTimeParserImpl : DateTimeParser {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    override fun parseDate(value: String): LocalDate {
        return LocalDate.parse(value, dateFormatter)
    }

    override fun parseTime(value: String): LocalTime {
        return LocalTime.parse(value, timeFormatter)
    }

}