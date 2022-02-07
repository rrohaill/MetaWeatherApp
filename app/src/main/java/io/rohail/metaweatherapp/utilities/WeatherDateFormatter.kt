package io.rohail.metaweatherapp.utilities

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object WeatherDateFormatter {

    @SuppressLint("ConstantLocale")
    private val locale = Locale.getDefault()

    private val fullDateTimeFormatter =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale)

    private const val dayMonthYearNameFullPattern = "dd MMM yyyy"
    private val dayMonthYearNameFullFormatterSimple =
        SimpleDateFormat(dayMonthYearNameFullPattern, locale)
    private val hourMinuteFormatter = SimpleDateFormat("HH:mm", locale)

    private fun getFormattedDateObject(dateString: String): Date? =
        fullDateTimeFormatter.parse(dateString)

    fun getFormattedDayMonthYear(date: String): String =
        when (val formattedDate: Date? = getFormattedDateObject(date)) {
            null -> date
            else -> dayMonthYearNameFullFormatterSimple.format(formattedDate)
        }

    fun getHourMinute(date: String): String =
        when (val formattedDate: Date? = getFormattedDateObject(date)) {
            null -> date
            else -> hourMinuteFormatter.format(formattedDate)
        }

}