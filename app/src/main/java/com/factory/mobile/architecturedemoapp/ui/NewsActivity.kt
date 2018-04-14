package com.factory.mobile.architecturedemoapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.factory.mobile.architecturedemoapp.adapter.NewsAdapter
import com.factory.mobile.architecturedemoapp.R
import com.factory.mobile.architecturedemoapp.databinding.ActivityNewsBinding
import com.factory.mobile.architecturedemoapp.model.Article
import com.factory.mobile.architecturedemoapp.viewmodel.NewsViewModel

/**
 * Created by maxence bourdin on 14/04/2018.
 */
class NewsActivity : AppCompatActivity() {
    private lateinit var newsBinding: ActivityNewsBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsBinding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        initDataBinding()
        initRecyclerView()
        loadData()
    }

    private fun initDataBinding(){
        newsBinding.newsViewModel = newsViewModel
    }

    private fun initRecyclerView(){
        newsBinding.newsList.layoutManager = LinearLayoutManager(baseContext)
        newsAdapter = NewsAdapter(ArrayList())
        newsBinding.newsList.adapter = newsAdapter
    }

    private fun loadData(){
        newsViewModel.loadData("le-monde")
        newsViewModel.articlesList.observe(this, Observer{
            handleResponse(ArrayList(it))
        } )
    }

    private fun handleResponse(articlesList : ArrayList<Article>) {
        newsAdapter.setValue(articlesList)
    }
}
