package com.factory.mobile.architecturedemoapp.viewmodel

import android.databinding.BaseObservable
import com.factory.mobile.architecturedemoapp.model.Article

/**
 * Created by maxence bourdin on 14/04/2018.
 */
class ItemArticleViewModel(private var article: Article) : BaseObservable() {
    val articleName: String
        get() {
            return  article.title
        }

    val articleSource: String
        get(){
            return "Source : ${article.source.name}"
        }

    val articleDescription: String
        get() {
            return article.description
        }

    fun setArticle(article: Article) {
        this.article = article
        notifyChange()
    }
}
