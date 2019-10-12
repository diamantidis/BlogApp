package io.github.diamantidis.feedreader.model

import kotlinx.serialization.Serializable

@Serializable
data class Author (
    val name: String,
    val url: String
)