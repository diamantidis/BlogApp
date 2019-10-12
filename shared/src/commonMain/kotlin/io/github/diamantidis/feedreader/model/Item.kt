package io.github.diamantidis.feedreader.model

import io.ktor.util.date.GMTDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import io.github.diamantidis.feedreader.utils.parseDate

@Serializable
data class Item (
    val id: String,
    val url: String,
    val title: String,
    @SerialName("date_published")
    val datePublishedStr: String? = null,
    @SerialName("date_modified")
    val dateModifiedStr: String? = null,
    val author: Author? = null,
    val summary: String? = null,
    @SerialName("content_html")
    val contentHtml: String? = null
) {
    @Transient
    val datePublished: GMTDate?
        get() = datePublishedStr?.parseDate()
    @Transient
    val dateModified: GMTDate?
        get() = dateModifiedStr?.parseDate()
}
