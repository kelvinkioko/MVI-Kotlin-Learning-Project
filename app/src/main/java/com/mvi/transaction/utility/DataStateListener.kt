package com.mvi.transaction.utility

interface DataStateListener {
    fun onDataStateChange(dataState: DataState<*>?)
}