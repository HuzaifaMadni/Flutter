package com.huzaifa.weatherapp.data.db.repository

import androidx.lifecycle.LiveData
import com.huzaifa.weatherapp.data.db.entity.CurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather() : LiveData<CurrentWeatherEntry>
}