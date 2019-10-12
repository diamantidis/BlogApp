package io.github.diamantidis.feedreader.factory

import io.github.diamantidis.feedreader.presentation.FeedView
import io.github.diamantidis.feedreader.presenter.FeedPresenter

class PresenterFactory {
    companion object {
        fun createFeedPresenter(view: FeedView) = FeedPresenter(view)
    }
}