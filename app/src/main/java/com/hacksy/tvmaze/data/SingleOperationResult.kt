package com.hacksy.tvmaze.data

sealed class SingleOperationResult<out T> {
    data class Success<T>(val data: T?) : SingleOperationResult<T>()
    data class Error(val exception:Exception?) : SingleOperationResult<Nothing>()
}