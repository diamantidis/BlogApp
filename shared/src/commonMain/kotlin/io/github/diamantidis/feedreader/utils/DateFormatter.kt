package io.github.diamantidis.feedreader.utils

import io.ktor.util.date.GMTDate
import io.ktor.util.date.Month

internal fun String.parseDate(): GMTDate {
    val dateRegex = "^(-?(?:[1-9][0-9]*)?[0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\\\.[0-9]+)?(Z)?".toRegex()

    val matchResult = dateRegex.find(this)

    matchResult?.groupValues?.let {
        val year = it.get(1).toInt()
        val month = it[2].toInt()
        val day = it[3].toInt()
        val hour = it[4].toInt()
        val minute = it[5].toInt()
        val second = it[6].toInt()

        return GMTDate(second, minute, hour, day, Month.from(month - 1), year)
    } ?: run {
        throw Error("Error while parsing $this")
    }
}
