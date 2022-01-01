package com.aqwas.com.sa.network

import com.aqwas.com.sa.helper.Constants
import com.aqwas.com.sa.model.TrendingRepositoriesModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getTrendingRepositories():Response<ArrayList<TrendingRepositoriesModel>>

}