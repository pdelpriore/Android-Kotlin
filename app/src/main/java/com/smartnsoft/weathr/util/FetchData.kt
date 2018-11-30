package com.smartnsoft.weathr.util

import android.content.Context
import android.provider.SyncStateContract
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.smartnsoft.weathr.R
import com.smartnsoft.weathr.model.Data

public class FetchData {

    interface DataFetchCallback {
        fun onDataFetched(data: Data?)
    }

    companion object {

        fun fetchDataApi(context: Context, valueCity: String, valueForecast: String, callback: DataFetchCallback) {

            var dataApi : Data?

            val isNetworkAvailable = Network.isNetworkAvailable(context)

            if (isNetworkAvailable == true) {

                val queue = Volley.newRequestQueue(context)

                val url = context.getString(R.string.url_api, valueCity, valueForecast)

                val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->

                    dataApi =  Gson().fromJson(response, Data::class.java)

                    callback.onDataFetched(dataApi)

                }, Response.ErrorListener {

                    response -> Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                })

                queue.add(stringRequest)
            }
        }
    }
}