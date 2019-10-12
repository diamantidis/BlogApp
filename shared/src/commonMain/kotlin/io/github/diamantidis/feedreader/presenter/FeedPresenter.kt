package io.github.diamantidis.feedreader.presenter

import kotlinx.coroutines.*
import io.github.diamantidis.feedreader.ApplicationScope
import io.github.diamantidis.feedreader.model.Feed
import io.github.diamantidis.feedreader.network.Api
import io.github.diamantidis.feedreader.presentation.FeedView

class FeedPresenter(
    private val view: FeedView,
    private val api: Api = Api(),
    private val mainScope: CoroutineScope = ApplicationScope()
) {
    fun loadData() {
        view.showLoading()

        mainScope.launch {
            try {
                val result: Feed = api.fetchFeed()
                view.showData(result)
            } catch (error: Throwable) {
                view.showError(error)
            }
        }
    }

    fun destroy() = mainScope.cancel()
}
