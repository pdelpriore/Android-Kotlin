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
import com.smartnsoft.weathr.model.Forecast
import com.smartnsoft.weathr.model.Weather
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter (val myDataset: List<Forecast>, val context: Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val itemDate = view.item_date
        val itemTemp = view.item_temp
        val image = view.item_type

        val viewItem = view.card

    }

    override fun getItemCount(): Int {

        return myDataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate = dateFormat.parse(myDataset[position].date)

        val date = SimpleDateFormat("EEEE dd MMMM", Locale.FRANCE).format(parsedDate)

        holder.viewItem.setBackgroundColor(ContextCompat.getColor(context, Weather.getColorByType(myDataset[position].type)))

        holder.itemDate.text = date.capitalize()
        holder.image.setImageResource(Weather.getPictureByName(myDataset[position].type))
        holder.itemTemp.text = myDataset.get(position).temperatureMax.toString() + context.getString(R.string.degree)

        holder.viewItem.setOnClickListener { v ->

            val dataset = myDataset[position]

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("ForecastExtra", dataset)

            context.startActivity(intent)
        }
    }
}