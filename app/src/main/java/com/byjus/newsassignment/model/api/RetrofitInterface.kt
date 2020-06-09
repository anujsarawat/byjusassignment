package com.byjus.newsassignment.model.api

import com.byjus.newsassignment.model.datamodels.TopHeadlines
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("v2/top-headlines")
    fun getHeadlines(): Single<TopHeadlines>
}