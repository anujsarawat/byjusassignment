package com.byjus.newsassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.byjus.newsassignment.model.datamodels.TopHeadlines
import com.byjus.newsassignment.model.api.NetworkState
import com.byjus.newsassignment.model.repository.NewsListRepository
import io.reactivex.disposables.CompositeDisposable

class NewsListViewModel (application: Application)  : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private val newsListRepository = NewsListRepository()

    val newsList : LiveData<TopHeadlines> by lazy {
        newsListRepository.fetchNewsList(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        newsListRepository.getNewsListNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}