package com.example.retrofitapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapp.network.MarsApi
import com.example.retrofitapp.network.MarsProperty
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class OverviewViewModel: ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>> get() = _properties

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            try {
                val getPropertiesDeferred = MarsApi.retrofitService.getProperties()
                val listResult = getPropertiesDeferred.await()
                if (listResult.isNotEmpty()) {
                    _properties.value = listResult
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
        _status.value = "Set the Mars API Response here!"
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
