package com.huzaifa.weatherapp.data.db.repository

import androidx.lifecycle.LiveData
import com.huzaifa.weatherapp.data.db.WeatherDAO
import com.huzaifa.weatherapp.data.db.entity.CurrentWeatherEntry
import com.huzaifa.weatherapp.data.network.WeatherNetworkDataSource
import com.huzaifa.weatherapp.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ForecastRepositoryImpl(
    private val weatherDAO: WeatherDAO,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadCurrentWeather.observeForever{ newCurrentWeather ->
            persistantWeather(newCurrentWeather)
        }
    }
    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun persistantWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            weatherDAO.upsert(fetchedWeather.currentWeatherEntry)
        }
    }
}