package com.mvi.transaction.database.api

import androidx.lifecycle.LiveData
import com.mvi.transaction.database.api.essentials.GenericApiResponse
import com.mvi.transaction.database.entity.UserEntity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {

    @POST("login")
    @FormUrlEncoded
    fun startUserSignin(@Field("email") email_address: String?, @Field("password") password: String?): Call<ResponseBody>

    @POST("register")
    @FormUrlEncoded
    fun startUserSignup(@Field("name") full_name: String?, @Field("email") email_address: String?, @Field("password") password: String?, @Field("c_password") c_password: String?): Call<ResponseBody>
}