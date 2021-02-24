package com.test.worldwether.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.test.worldwether.data.request.WeatherRequest
import com.test.worldwether.data.service.WeatherService
import io.reactivex.disposables.CompositeDisposable

class HomeScreenViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private var response: JsonObject? = null

    private val _serviceState = MutableLiveData<Boolean>()
    val serviceState: LiveData<Boolean> = _serviceState

    fun getWeatherService() {
        compositeDisposable.add(
            WeatherService().getWeatherApi(WeatherRequest()).subscribe({
                runCatching {
                    _serviceState.value = it != null
                    response = it
                }.onFailure {
                    _serviceState.value = false
                    response = null
                }
            },{
                _serviceState.value = false
                response = null
            })
        )
    }



}