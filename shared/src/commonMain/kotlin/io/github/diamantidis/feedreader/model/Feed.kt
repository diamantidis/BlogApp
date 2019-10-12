package io.github.diamantidis.feedreader.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feed (
    val version: String,
    val title: String,
    @SerialName("home_page_url")
    val homePageURL: String,
    @SerialName("feed_url")
    var feedURL: String,
    val description: String,
    val icon: String? = null,
    val favicon: String? = null,
    var expired: Boolean? = null,
    val author: Author,
    val items: List<Item>
)