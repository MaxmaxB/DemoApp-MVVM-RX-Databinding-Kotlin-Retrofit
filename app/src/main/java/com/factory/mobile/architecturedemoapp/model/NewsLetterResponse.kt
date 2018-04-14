package com.factory.mobile.architecturedemoapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by maxence bourdin on 14/04/2018.
 */
data class NewsResponse(
        @SerializedName("status") val status: String,
        @SerializedName("totalResults") val totalResults: Int,
        @SerializedName("articles") val articles: List<Article>
)

data class Article(
        @SerializedName("source") val source: NewsSource,
        @SerializedName("author") val author: String,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("url") val url: String,
        @SerializedName("urlToImage") val urlToImage: String,
        @SerializedName("publishedAt") val publishedAt: String
)

data class NewsSource(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String
)