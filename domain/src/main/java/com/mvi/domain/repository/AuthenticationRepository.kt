package com.mvi.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvi.domain.api.RetrofitRequestBuilder
import com.mvi.domain.entity.UserEntity
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AuthenticationRepository {

    fun getUserSignin(email_address: String, password: String): LiveData<DataState<SigninViewState>> {
        val responseViewState: MutableLiveData<DataState<SigninViewState>> = MutableLiveData()
        responseViewState.value = DataState.loading(true)
        RetrofitRequestBuilder.apiService.startUserSignin(email_address, password)
            .enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    //handle error here
                    responseViewState.value = DataState.error(
                        message = t.message.toString()
                    )
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    //your raw string response
                    val stringResponse = response.body()?.string()
                    println("Debug: Signin Datastate: $stringResponse")
                    try {
                        val jObj = JSONObject(stringResponse!!)
                        if (jObj.getString("success") == "true") {
                            println("Debug: Signin Success: ${jObj.getString("data")}")
                            val dataObject = jObj.getJSONObject("data")
                            val userObject = dataObject.getJSONObject("user")
                            val userEntity = UserEntity(
                                0,
                                userObject.getString("user_id"),
                                userObject.getString("name"),
                                userObject.getString("email"),
                                userObject.getString("phonenumber"),
                                userObject.getString("updated_at"),
                                userObject.getString("created_at")
                            )

                            responseViewState.value = DataState.success(
                                message = null,
                                data = SigninViewState( userEntity = userEntity )
                            )
                        } else {
                            println("Debug: Signin fail: ${jObj.getString("message")}")
                            responseViewState.value = DataState.error(
                                message = jObj.getString("message")
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

            })
        return responseViewState
    }

    fun getUserSignup(full_name: String, email_address: String, password: String, c_password: String): LiveData<DataState<SigninViewState>> {
        val responseViewState: MutableLiveData<DataState<SigninViewState>> = MutableLiveData()
        responseViewState.value = DataState.loading(true)
        RetrofitRequestBuilder.apiService.startUserSignup(full_name, email_address, password, c_password)
            .enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    //handle error here
                    responseViewState.value = DataState.error(
                        message = t.message.toString()
                    )
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    //your raw string response
                    val stringResponse = response.body()?.string()
                    println("Debug: Signin Datastate: ${stringResponse}")
                    try {
                        val jObj = JSONObject(stringResponse!!)
                        if (jObj.getString("success") == "true") {
                            println("Debug: Signin Success: ${jObj.getString("data")}")
                            val dataObject = jObj.getJSONObject("data")
                            val userObject = dataObject.getJSONObject("user")
                            val userEntity = UserEntity(
                                0,
                                userObject.getString("user_id"),
                                userObject.getString("name"),
                                userObject.getString("email"),
                                userObject.getString("phonenumber"),
                                userObject.getString("updated_at"),
                                userObject.getString("created_at")
                            )

                            responseViewState.value = DataState.success(
                                message = null,
                                data = SigninViewState( userEntity = userEntity )
                            )
                        } else {
                            println("Debug: Signin fail: ${jObj.getString("message")}")
                            responseViewState.value = DataState.error(
                                message = jObj.getString("message")
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

            })
        return responseViewState
    }
}