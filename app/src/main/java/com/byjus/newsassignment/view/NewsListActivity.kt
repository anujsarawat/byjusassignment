package com.byjus.newsassignment.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.byjus.newsassignment.R
import com.byjus.newsassignment.model.datamodels.Article
import com.byjus.newsassignment.model.datamodels.TopHeadlines
import com.byjus.newsassignment.model.api.NetworkState
import com.byjus.newsassignment.view.adapters.NewsListAdapter
import com.byjus.newsassignment.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity(), NewsListAdapter.NewsItemClickListener {
    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var topHeadlines: TopHeadlines

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        newsListViewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        setObservers()
    }

    private fun setObservers() {
        newsListViewModel.newsList.observe(this, Observer {
            topHeadlines = it
            bindUI(it)
        })

        newsListViewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    private fun bindUI(it: TopHeadlines){
        val newsListAdapter = NewsListAdapter(this, it.articles as ArrayList<Article>, this)
        recyclerViewNews.layoutManager = LinearLayoutManager(this)
        recyclerViewNews.adapter = newsListAdapter
    }

    override fun onNewsItemClicked(position: Int) {
        val intent = Intent(this, NewsDetailsActivity::class.java)
        intent.putExtra("ArticleDetails", topHeadlines.articles?.get(position))
        startActivity(intent)
    }
}
