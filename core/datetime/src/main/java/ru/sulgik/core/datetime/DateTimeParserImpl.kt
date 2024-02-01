package ru.sulgik.core.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
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

    override fun formatTime(time: LocalTime): String {
        return timeFormatter.format(time)
    }

    override fun formatDateWithWeek(time: LocalDate): String {
        return buildString {
            append(time.dayOfWeek.formatWeekShort())
            append(", ")
            append(time.dayOfMonth)
            append(" ")
            append(time.month.formatMonthShort())
        }
    }

    override fun formatDateWithTime(date: LocalDate, time: LocalTime): String {
        return buildString {
            append(date.dayOfMonth)
            append(" ${date.month.formatMonth()} ")
            append(formatTime(time))
        }
    }

    private fun DayOfWeek.formatWeekShort(): String {
        return when(this) {
            DayOfWeek.MONDAY -> "Пн"
            DayOfWeek.TUESDAY -> "Вт"
            DayOfWeek.WEDNESDAY -> "Ср"
            DayOfWeek.THURSDAY -> "Чт"
            DayOfWeek.FRIDAY -> "Пт"
            DayOfWeek.SATURDAY -> "Сб"
            DayOfWeek.SUNDAY -> "Вс"
        }
    }
    private fun Month.formatMonthShort(): String {
        return when(this) {
            Month.JANUARY -> "янв"
            Month.FEBRUARY -> "фев"
            Month.MARCH -> "мар"
            Month.APRIL -> "апр"
            Month.MAY -> "май"
            Month.JUNE -> "июл"
            Month.JULY -> "июн"
            Month.AUGUST -> "авг"
            Month.SEPTEMBER -> "сен"
            Month.OCTOBER -> "окт"
            Month.NOVEMBER -> "ноя"
            Month.DECEMBER -> "дек"
        }
    }

    private fun Month.formatMonth(): String {
        return when(this) {
            Month.JANUARY -> "января"
            Month.FEBRUARY -> "февраля"
            Month.MARCH -> "марта"
            Month.APRIL -> "апреля"
            Month.MAY -> "мая"
            Month.JUNE -> "июля"
            Month.JULY -> "июня"
            Month.AUGUST -> "августа"
            Month.SEPTEMBER -> "сентября"
            Month.OCTOBER -> "октября"
            Month.NOVEMBER -> "ноября"
            Month.DECEMBER -> "декабря"
        }
    }


}
