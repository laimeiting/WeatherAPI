package com.medicare4u.androidtake_homeexercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.medicare4u.androidtake_homeexercise.WeatherRepository
import com.medicare4u.androidtake_homeexercise.WeatherResponse
import android.util.Log

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

    fun fetchWeather(apiKey: String, city: String) {
        viewModelScope.launch {
            try {
                val response = repository.getWeather(apiKey, city)
                _weather.postValue(response)
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather: ${e.message}")
            }
        }
    }
}
