package com.hacksy.tvmaze.model

import com.hacksy.tvmaze.data.OperationResult
import com.hacksy.tvmaze.data.remote.ApiClient
import com.hacksy.tvmaze.data.remote.TvShowsRemoteDataSource

class TvShowsRemoteRepository: TvShowsRemoteDataSource {

    override suspend fun listTvShows(page: Int): OperationResult<TvShows> {
        try {
            val response = ApiClient.build()?.listTvShows(page)
            response?.let {
                return if(it.isSuccessful && it.body()!=null){
                    val data = it.body()
                    OperationResult.Success(data?.map { it ->
                        it.show;

                    });
                }else{
                    val message = "Error message"
                    OperationResult.Error(Exception(message))
                }
            }?:run{
                return OperationResult.Error(Exception("Ocurrió un error"))
            }
        }catch (e:Exception){
            return OperationResult.Error(e)
        }
    }

    override suspend fun searchTvShows(show: String): OperationResult<TvShows> {
        try {
            val response = ApiClient.build()?.searchTvShows(show)
            response?.let {
                return if(it.isSuccessful && it.body()!=null){
                    val data = it.body()
                    OperationResult.Success(data?.map { it ->
                        it.show;

                    });
                }else{
                    val message = "Error message"
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