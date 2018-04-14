package com.factory.mobile.architecturedemoapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.util.Log
import com.factory.mobile.architecturedemoapp.manager.NewsManager
import com.factory.mobile.architecturedemoapp.model.Article
import com.factory.mobile.architecturedemoapp.model.NewsResponse

/**
 * Created by maxence bourdin on 14/04/2018.
 */
class NewsViewModel : ViewModel(){
    val isLoading = ObservableBoolean(true)
    val error = ObservableBoolean(false)

    var articlesList : MutableLiveData<List<Article>> = MutableLiveData()

    fun loadData(sourceId : String) {
        NewsManager.getInstance(sourceId).getTrends(object : NewsManager.getTrendsListener {
            override fun onTrendGetSuccess(newsResponse: NewsResponse) {
                newsResponse.articles.let {
                    articlesList.value = it
                }
                isLoading.set(false)
            }

            override fun onTrendGetFail(message: String) {
                isLoading.set(false)
                error.set(true)
                Log.e("NewsViewModel", message)
            }
        })
    }

}