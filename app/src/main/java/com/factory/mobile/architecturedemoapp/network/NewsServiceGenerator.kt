package com.factory.mobile.architecturedemoapp.network

import com.factory.mobile.architecturedemoapp.manager.ContextManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by maxence bourdin on 14/04/2018.
 */
class NewsServiceGenerator{
    companion object {
        private var mBaseUrl: String = "https://newsapi.org/"

        private fun getCustomRetrofit(): Retrofit {
            val gsonMalformed: Gson = GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
            return Retrofit.Builder()
                    .client(makeOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gsonMalformed))
                    .build()
        }

        private fun makeOkHttpClient(): OkHttpClient {
            return with(OkHttpClient.Builder()) {
                ContextManager.getContext()?.let {
                    this.addInterceptor(ChuckInterceptor(it))
                }
                this.build()
            }
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getCustomRetrofit().create(serviceClass)
        }

    }
}
