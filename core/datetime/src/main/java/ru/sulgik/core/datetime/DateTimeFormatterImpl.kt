package ru.sulgik.core.datetime

import android.content.Context
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toKotlinLocalTime
import java.time.DayOfWeek
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.LocalDate as JavaLocalDate
import java.time.LocalTime as JavaLocalTime

class DateTimeFormatterImpl(
    private val context: Context,
) : ru.sulgik.core.datetime.DateTimeFormatter {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    override fun parseDate(value: String): LocalDate {
        return JavaLocalDate.parse(value, dateFormatter).toKotlinLocalDate()
    }

    override fun parseTime(value: String): LocalTime {
        return JavaLocalTime.parse(value, timeFormatter).toKotlinLocalTime()
    }

    override fun formatTime(time: LocalTime): String {
        return timeFormatter.format(time.toJavaLocalTime())
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
        return when (this) {
            DayOfWeek.MONDAY -> context.getString(R.string.monday_short)
            DayOfWeek.TUESDAY -> context.getString(R.string.tuesday_short)
            DayOfWeek.WEDNESDAY -> context.getString(R.string.wednesday_short)
            DayOfWeek.THURSDAY -> context.getString(R.string.thursday_short)
            DayOfWeek.FRIDAY -> context.getString(R.string.friday_short)
            DayOfWeek.SATURDAY -> context.getString(R.string.saturday_short)
            DayOfWeek.SUNDAY -> context.getString(R.string.sunday_short)
        }
    }

    private fun Month.formatMonthShort(): String {
        return when (this) {
            Month.JANUARY -> context.getString(R.string.january_short)
            Month.FEBRUARY -> context.getString(R.string.february_short)
            Month.MARCH -> context.getString(R.string.march_short)
            Month.APRIL -> context.getString(R.string.april_short)
            Month.MAY -> context.getString(R.string.may_short)
            Month.JUNE -> context.getString(R.string.june_short)
            Month.JULY -> context.getString(R.string.july_short)
            Month.AUGUST -> context.getString(R.string.august_short)
            Month.SEPTEMBER -> context.getString(R.string.september_short)
            Month.OCTOBER -> context.getString(R.string.october_short)
            Month.NOVEMBER -> context.getString(R.string.november_short)
            Month.DECEMBER -> context.getString(R.string.december_short)
        }
    }

    private fun Month.formatMonth(): String {
        return when (this) {
            Month.JANUARY -> context.getString(R.string.january)
            Month.FEBRUARY -> context.getString(R.string.february)
            Month.MARCH -> context.getString(R.string.march)
            Month.APRIL -> context.getString(R.string.april)
            Month.MAY -> context.getString(R.string.may)
            Month.JUNE -> context.getString(R.string.june)
            Month.JULY -> context.getString(R.string.july)
            Month.AUGUST -> context.getString(R.string.august)
            Month.SEPTEMBER -> context.getString(R.string.september)
            Month.OCTOBER -> context.getString(R.string.october)
            Month.NOVEMBER -> context.getString(R.string.november)
            Month.DECEMBER -> context.getString(R.string.december)
        }
    }


}
