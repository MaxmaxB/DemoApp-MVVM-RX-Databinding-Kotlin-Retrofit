package com.factory.mobile.architecturedemoapp.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.factory.mobile.architecturedemoapp.R
import com.factory.mobile.architecturedemoapp.databinding.ItemArticleRowBinding
import com.factory.mobile.architecturedemoapp.model.Article
import com.factory.mobile.architecturedemoapp.viewmodel.ItemArticleViewModel

/**
 * Created by maxence bourdin on 14/04/2018.
 */
class NewsAdapter(private var articleList : ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.ArticleAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapterViewHolder {
        val itemArticleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_article_row, parent, false) as ItemArticleRowBinding
        return ArticleAdapterViewHolder(itemArticleBinding)
    }

    override fun onBindViewHolder(holder: ArticleAdapterViewHolder, position: Int) {
        holder.bindArticle(articleList[position])
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setValue(articleList : ArrayList<Article>){
        this.articleList = articleList
        notifyDataSetChanged()
    }

    class ArticleAdapterViewHolder(private var mItemArticleBinding: ItemArticleRowBinding) : RecyclerView.ViewHolder(mItemArticleBinding.itemArticle) {
        fun bindArticle(article: Article) {
            if (mItemArticleBinding.articleViewModel == null)
                mItemArticleBinding.articleViewModel  = ItemArticleViewModel(article)
            else
                mItemArticleBinding.articleViewModel!!.setArticle(article)
        }
    }
}
