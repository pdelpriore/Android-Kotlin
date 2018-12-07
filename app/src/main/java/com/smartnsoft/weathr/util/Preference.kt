package com.smartnsoft.weathr.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Preference {


        val CITY_NAME = "city"
        val FORECASTS = "forecasts"

        fun getPreference (context: Context) : SharedPreferences {

            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun setCity (context: Context, cityText: String?) {

            getPreference(context).edit().putString(CITY_NAME, cityText).apply()
        }

        fun getCity (context: Context) : String? {

            return getPreference(context).getString(CITY_NAME, null)
        }

        fun setForecasts (context: Context, forecastsText: String?) {

            getPreference(context).edit().putString(FORECASTS, forecastsText).apply()
        }

        fun getForecasts (context: Context) : String? {

            return getPreference(context).getString(FORECASTS, null)
        }
}