package com.mvi.transaction.utility

/*
 *  Data state class
 *
 * variables message, loading, data
 */
data class DataState<T>(var message: Event<String>? = null, var loading: Boolean = false, var data: Event<T>? = null) {
    companion object {
        /*
         * Incase of an failed network request the error function will return a
         * message with a value
         * Loading will be false - to hide the loading dialog
         * data will be null because there is no data to return
         */
        fun <T> error(message: String): DataState<T> {
            return DataState(message = Event(message), loading = false, data = null )
        }

        /*
         * Incase of a network request the loading function will return a
         * message will be null since there is no network message to return
         * Loading will be isLoading where isLoading can be either false or true - to hide or display the loading dialog
         * data will be null because there is no data we want to return
         */
        fun <T> loading(isLoading: Boolean): DataState<T> {
            return DataState(message = null, loading = isLoading, data = null )
        }

        /*
         * Incase of an successful network request the success function will return a
         * message will be null since there is no network message to return
         * Loading will be false - to hide the loading dialog
         * data will be null because there is no data we want to return
         */
        fun <T> success(message: String? = null, data: T? = null): DataState<T> {
            return DataState(message = Event.messageEvent(message), loading = false, data = Event.dataEvent(data) )
        }
    }

    override fun toString(): String {
        return "DataState(message=$message,loading=$loading,data=$data)"
    }
}