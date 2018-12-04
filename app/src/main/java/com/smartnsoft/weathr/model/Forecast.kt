package com.smartnsoft.weathr.model

import java.io.Serializable

data class Forecast
    ( val date: String,
        val temperatureMax: Int,
        val temperatureMin: Int,
        val type: String,
        val uvIndex: Int,
        val windDirection: String,
        val windSpeed: Int
): Serializable