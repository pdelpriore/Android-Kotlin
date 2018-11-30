package com.smartnsoft.weathr.model

data class Forecast(
        val date: String,
        val temperatureMax: Int,
        val temperatureMin: Int,
        val type: String,
        val uvIndex: Int,
        val windDirection: String,
        val windSpeed: Int
)