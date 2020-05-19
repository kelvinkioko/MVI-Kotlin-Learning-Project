package com.mvi.transaction.database.api

import com.mvi.transaction.database.api.essentials.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRequestBuilder {

    const val BASE_URL: String = "http://165.227.48.54/api/"

    /*
    *   'By lazy' just means only initialize this val once and anytime its needed, use the initialized values.
    * */
    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
    }


    val apiService: ApiService by lazy{
        retrofitBuilder.build().create(ApiService::class.java)
    }
}