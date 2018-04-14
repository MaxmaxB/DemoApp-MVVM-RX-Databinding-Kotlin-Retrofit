package com.factory.mobile.architecturedemoapp.manager

import com.factory.mobile.architecturedemoapp.model.NewsResponse
import com.factory.mobile.architecturedemoapp.network.NewsService
import com.factory.mobile.architecturedemoapp.network.NewsServiceGenerator
import retrofit2.Call
import retrofit2.Response

/**
 * Created by maxence bourdin on 14/04/2018.
 */
class NewsManager(private var sourceId: String) {
    private var newsService = NewsServiceGenerator.createService(NewsService::class.java)

    companion object {

        private var mInstance: NewsManager? = null

        fun getInstance(sourceId: String): NewsManager {
            if (mInstance == null) {
                mInstance = NewsManager(sourceId)
            }else{
                mInstance!!.sourceId = sourceId
            }
            return mInstance as NewsManager
        }
    }


    fun getTrends(callback: getTrendsListener) {
        val apiKey = "7f8eff1f80ac4b0ba6a3ef5d314bad42"

        val call = newsService.getArticles(sourceId, apiKey)
        call.enqueue(object : retrofit2.Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val newsResponseModel = response.body()
                    callback.onTrendGetSuccess(newsResponseModel!!)
                } else {
                    callback.onTrendGetFail(response.message())
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                callback.onTrendGetFail(t.message!!)
            }
        })
    }

    interface getTrendsListener {
        fun onTrendGetSuccess(newsResponse: NewsResponse)
        fun onTrendGetFail(message: String)
    }
}