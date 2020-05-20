package com.mvi.transaction.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mvi.transaction.database.api.RetrofitRequestBuilder
import com.mvi.transaction.database.api.essentials.ApiEmptyResponse
import com.mvi.transaction.database.api.essentials.ApiErrorResponse
import com.mvi.transaction.database.api.essentials.ApiSuccessResponse
import com.mvi.transaction.database.api.essentials.GenericApiResponse
import com.mvi.transaction.database.entity.UserEntity
import com.mvi.transaction.ui.auth.login.state.SigninViewState
import com.mvi.transaction.utility.DataState
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AuthenticationRepository {

    fun getUserSignin(email_address: String, password: String): LiveData<SigninViewState> {
        return Transformations.switchMap(RetrofitRequestBuilder.apiService.startUserSignin(email_address, password)){
            apiResponse ->
            object : LiveData<SigninViewState>() {
                override fun onActive() {
                    super.onActive()
                    value = when(apiResponse){
                        is ApiSuccessResponse -> {
                            SigninViewState( userEntity = apiResponse.body )
                        }

                        is ApiErrorResponse -> {
                            SigninViewState() //Handle Error
                        }

                        is ApiEmptyResponse -> {
                            SigninViewState() //Handle Error
                        }
                    }
                }
            }
        }
    }

    fun getUserSignin2(email_address: String, password: String): LiveData<DataState<SigninViewState>> {
        val responseViewState: MutableLiveData<DataState<SigninViewState>> = MutableLiveData()
        RetrofitRequestBuilder.apiService.startUserSignin2(email_address, password)
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

//                            responseViewState.value = SigninViewState( userEntity = userEntity )
                            responseViewState.value = DataState.success(
                                message = null,
                                data = SigninViewState( userEntity = userEntity )
                            )
                        } else {
                            println("Debug: Signin Failure: ${jObj.getString("message")}")
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

            })
        return responseViewState
    }
}