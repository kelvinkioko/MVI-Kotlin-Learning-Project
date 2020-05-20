package com.mvi.transaction.database.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mvi.transaction.database.api.essentials.ApiEmptyResponse
import com.mvi.transaction.database.api.essentials.ApiErrorResponse
import com.mvi.transaction.database.api.essentials.ApiSuccessResponse
import com.mvi.transaction.database.api.essentials.GenericApiResponse
import com.mvi.transaction.utility.Constants.Companion.TESTING_NETWORK_DELAY
import com.mvi.transaction.utility.DataState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResponseObject, ViewStateType> {

    protected val result = MediatorLiveData<DataState<ViewStateType>>()

    init {
        result.value = DataState.loading(true)

        GlobalScope.launch(IO){
            delay(TESTING_NETWORK_DELAY)

            withContext(Main){
                val apiResponse = createCall()
//                println("DEBUG: NetworkBoundResource: ${apiResponse.}")
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)

                    handleNetworkCall(response)
                }
            }
        }
    }

    private fun handleNetworkCall(response: GenericApiResponse<ResponseObject>){

        when(response){
            is ApiSuccessResponse ->{
                println("DEBUG: Network response: ${response}")
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse ->{
                println("DEBUG: NetworkBoundResource: ${response.errorMessage}")
                onReturnError(response.errorMessage)
            }
            is ApiEmptyResponse ->{
                println("DEBUG: NetworkBoundResource: Request returned NOTHING (HTTP 204)")
                onReturnError("HTTP 204. Returned NOTHING.")
            }
        }
    }

    private fun onReturnError(message: String){
        result.value = DataState.error(message)
    }

    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

    fun asLiveData() = result as LiveData<DataState<ViewStateType>>
}