package com.aqwas.com.sa.repository
import com.aqwas.com.sa.network.ApiService
import javax.inject.Inject

class TrendingRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getTrendingRepositories() = apiService.getTrendingRepositories()
}