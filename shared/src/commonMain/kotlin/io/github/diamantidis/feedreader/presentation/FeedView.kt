package io.github.diamantidis.feedreader.presentation

import io.github.diamantidis.feedreader.model.Feed

interface FeedView {
    fun showData(feed: Feed)
    fun showError(error: Throwable)
    fun showLoading()
}