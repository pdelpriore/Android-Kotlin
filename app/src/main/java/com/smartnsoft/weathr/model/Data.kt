package com.smartnsoft.weathr.model

import java.io.Serializable

data class Data(
        val city: String,
        val code: Int,
        val forecasts: List<Forecast>,
        val message: String
): Serializable
