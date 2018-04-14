package com.factory.mobile.architecturedemoapp.network

import com.factory.mobile.architecturedemoapp.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by maxence bourdin on 14/04/2018.
 */
interface NewsService {
    @GET("/v2/top-headlines")
    fun getArticles(@Query("sources") sourceId: String, @Query("apiKey") apiKey : String): Call<NewsResponse>
}