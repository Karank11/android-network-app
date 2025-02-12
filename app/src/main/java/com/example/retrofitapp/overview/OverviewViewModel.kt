package com.example.retrofitapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapp.network.MarsApi
import com.example.retrofitapp.network.MarsProperty
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


enum class MarsApiStatus {ERROR, LOADING, DONE}

class OverviewViewModel: ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus> get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>> get() = _properties

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            try {
                _status.value = MarsApiStatus.LOADING
                val getPropertiesDeferred = MarsApi.retrofitService.getProperties()
                val listResult = getPropertiesDeferred.await()
                if (listResult.isNotEmpty()) {
                    _properties.value = listResult
                }
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
