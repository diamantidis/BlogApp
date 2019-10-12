package io.github.diamantidis.feedreader.network

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import io.github.diamantidis.feedreader.model.Feed

class Api {
    private val client: HttpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict).apply {
                setMapper(Feed::class, Feed.serializer())
            }
        }
    }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom("https://diamantidis.github.io/")
            encodedPath = path
        }
    }

    suspend fun fetchFeed(): Feed = client.get {
        apiUrl("feed.json")
    }
}