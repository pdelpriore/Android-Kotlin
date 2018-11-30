package com.smartnsoft.weathr.model

data class Data(
        val city: String,
        val code: Int,
        val forecasts: List<Forecast>,
        val message: String
)
