package com.byjus.newsassignment.model.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.byjus.newsassignment.model.datamodels.TopHeadlines
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NetworkDataSource (private val apiService : RetrofitInterface, private val compositeDisposable: CompositeDisposable) {

    private val _networkState  = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState                   //with this get, no need to implement get function to get networkSate

    private val _apiResponse =  MutableLiveData<TopHeadlines>()
    val apiResponse: LiveData<TopHeadlines>
        get() = _apiResponse

    fun fetchNewsList() {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getHeadlines()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _apiResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                        }
                    )
            )
        }
        catch (e: Exception){
            _networkState.postValue(NetworkState.ERROR)
        }
    }
}