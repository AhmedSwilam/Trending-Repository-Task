package com.aqwas.com.sa.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqwas.com.sa.model.TrendingRepositoriesModel
import com.aqwas.com.sa.repository.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class TrendingRepositoriesViewModel
@Inject constructor(private val repository: TrendingRepository) : ViewModel() {

    private val _response = MutableLiveData <List<TrendingRepositoriesModel>>()
    val responseReposLiveData: LiveData<List<TrendingRepositoriesModel>> get() = _response

    init {
        getAllTrendingRepos()
    }

    fun getAllTrendingRepos() = viewModelScope.launch {
        repository.getTrendingRepositories().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("TAG", "getAllTrendingRepos: ${response.message()}")
            }
        }
    }
}