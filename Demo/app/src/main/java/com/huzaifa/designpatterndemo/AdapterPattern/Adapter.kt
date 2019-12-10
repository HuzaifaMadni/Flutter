package com.huzaifa.designpatterndemo.AdapterPattern

import org.json.JSONObject

class Adapter {

    private var obj : JSONObject = JSONObject()

    fun createJSONObject(data: String) {

        obj = JSONObject(data)
    }

    fun getTemp() : String {

        val mainObj = obj.getJSONObject("main")
        return mainObj.getString("temp")
    }

    fun getCityName() : String {

        return obj.getString("name")
    }

    fun getWeather() : String {

        val weatherObj = obj.getJSONArray("weather").getJSONObject(0)
        return weatherObj.getString("description")
    }
}