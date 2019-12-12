package com.huzaifa.weatherapp.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.huzaifa.weatherapp.data.network.response.CurrentWeatherResponse
import com.huzaifa.weatherapp.data.network.response.NoConnectivityException
import org.kodein.di.weakReference
import java.net.ConnectException
import java.nio.channels.NotYetConnectedException

class WeatherNetworkDataSourceImpl(
    private val weatherApiService: WeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedData = MutableLiveData<CurrentWeatherResponse>()
    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedData

    override suspend fun fetchCurrentWeather(location: String, units: String) {
        try {
            val fetchedCurrentWeather = weatherApiService
                .getCurrentWeather(location, units)
                .await()
            _downloadedData.postValue(fetchedCurrentWeather)
        } catch (e: NoConnectivityException){

        }
    }
}