package com.example.retrofitapp.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitapp.network.MarsProperty


class DetailViewModelFactory(val marsProperty: MarsProperty, val app: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(marsProperty, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}