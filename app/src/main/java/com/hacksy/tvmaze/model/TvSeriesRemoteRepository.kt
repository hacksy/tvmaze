package com.hacksy.tvmaze.model

import com.hacksy.tvmaze.data.OperationResult
import com.hacksy.tvmaze.data.remote.ApiClient
import com.hacksy.tvmaze.data.remote.TvSeriesRemoteDataSource

class TvSeriesRemoteRepository: TvSeriesRemoteDataSource {

    override suspend fun retrieveTvSeries(): OperationResult<TvSeries> {
        try {
            val response = ApiClient.build()?.tvSeries()
            response?.let {
                return if(it.isSuccessful && it.body()!=null){
                    val data = it.body()?.data
                    OperationResult.Success(data)
                }else{
                    val message = it.body()?.msg
                    OperationResult.Error(Exception(message))
                }
            }?:run{
                return OperationResult.Error(Exception("Ocurrió un error"))
            }
        }catch (e:Exception){
            return OperationResult.Error(e)
        }
    }

}