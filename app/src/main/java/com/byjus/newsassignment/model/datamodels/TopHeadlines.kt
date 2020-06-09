package com.byjus.newsassignment.model.datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TopHeadlines {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("totalResults")
    @Expose
    var totalResults: Long? = null

    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null
}