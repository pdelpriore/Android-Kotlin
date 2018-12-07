package com.smartnsoft.weathr.util

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smartnsoft.weathr.DetailActivity
import com.smartnsoft.weathr.R
import com.smartnsoft.weathr.model.Data
import com.smartnsoft.weathr.model.Forecast
import com.smartnsoft.weathr.model.Weather
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter (val myDataset: Data?, val context: Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val itemDate = view.item_date
        val itemTemp = view.item_temp
        val image = view.item_type

        val viewItem = view.card

    }

    override fun getItemCount(): Int {

        return myDataset?.forecasts?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate = dateFormat.parse(myDataset?.forecasts?.get(position)?.date)

        val date = SimpleDateFormat("EEEE dd MMMM", Locale.FRANCE).format(parsedDate)

        holder.viewItem.setBackgroundColor(ContextCompat.getColor(context, Weather.getColorByType(myDataset?.forecasts?.get(position)?.type)))

        holder.itemDate.text = date.capitalize()
        holder.image.setImageResource(Weather.getPictureByName(myDataset?.forecasts?.get(position)?.type))

        val itemTemp = myDataset?.forecasts?.get(position)?.temperatureMax

        holder.itemTemp.text = context.getString(R.string.degree, itemTemp)

        holder.viewItem.setOnClickListener { v ->

            val  city = myDataset?.city

            val listForecast = myDataset?.forecasts?.get(position)

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("CityExtra", city)
            intent.putExtra("ForecastExtra", listForecast)

            context.startActivity(intent)
        }
    }
}