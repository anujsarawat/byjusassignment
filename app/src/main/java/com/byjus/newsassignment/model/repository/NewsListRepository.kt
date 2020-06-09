package com.byjus.newsassignment.model.repository

import androidx.lifecycle.LiveData
import com.byjus.newsassignment.model.datamodels.TopHeadlines
import com.byjus.newsassignment.model.api.NetworkDataSource
import com.byjus.newsassignment.model.api.NetworkState
import com.byjus.newsassignment.model.api.RetrofitClient
import io.reactivex.disposables.CompositeDisposable

class NewsListRepository () {
    private lateinit var networkDataSource: NetworkDataSource
    private val apiService = RetrofitClient.getClient()

    fun fetchNewsList (compositeDisposable: CompositeDisposable) : LiveData<TopHeadlines> {
        networkDataSource = NetworkDataSource(apiService, compositeDisposable)
        networkDataSource.fetchNewsList()

        return networkDataSource.apiResponse
    }

    fun getNewsListNetworkState(): LiveData<NetworkState> {
        return networkDataSource.networkState
    }
}