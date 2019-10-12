package io.github.diamantidis.androidApp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import io.github.diamantidis.feedreader.factory.PresenterFactory
import io.github.diamantidis.feedreader.model.Feed
import io.github.diamantidis.feedreader.model.Item
import io.github.diamantidis.feedreader.presentation.FeedView
import io.github.diamantidis.feedreader.presenter.FeedPresenter

class MainActivity : AppCompatActivity(), FeedView {
    override fun showData(feed: Feed) {
        adapter.clear()
        adapter.addAll(feed.items)
    }

    override fun showLoading() {
        print("Loading")
    }

    override fun showError(error: Throwable) {
        print("show error: ${error.message}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = ListView(this)
        adapter = FeedAdapter(this, mutableListOf())
        listView.adapter = adapter

        presenter.loadData()

        this.setContentView(listView)
    }

    private lateinit var adapter: FeedAdapter
    private val presenter: FeedPresenter by lazy { PresenterFactory.createFeedPresenter(this) }

    private inner class FeedAdapter(context: Context, items: List<Item>) :
        ArrayAdapter<Item>(context, -1, -1, items) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val item = super.getItem(position)
            val listLayout = LinearLayout(context)
            listLayout.layoutParams = AbsListView.LayoutParams(
                AbsListView.LayoutParams.WRAP_CONTENT,
                AbsListView.LayoutParams.WRAP_CONTENT
            )
            val listText = TextView(context)
            listText.setPadding(40, 40, 0, 40)
            listText.text = item?.title

            listLayout.addView(listText)

            listLayout.setTag(item)
            return listLayout
        }
    }
}