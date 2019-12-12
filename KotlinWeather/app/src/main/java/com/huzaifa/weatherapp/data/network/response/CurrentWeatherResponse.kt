package com.huzaifa.weatherapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.huzaifa.weatherapp.data.db.entity.CurrentWeatherEntry
import com.huzaifa.weatherapp.data.db.entity.Location
import com.huzaifa.weatherapp.data.db.entity.Request


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)