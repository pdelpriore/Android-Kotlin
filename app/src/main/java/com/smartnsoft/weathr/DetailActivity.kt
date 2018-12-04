package com.smartnsoft.weathr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.smartnsoft.weathr.model.Forecast
import com.smartnsoft.weathr.model.Weather
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataset : Forecast = intent.getSerializableExtra("ForecastExtra") as Forecast

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate = dateFormat.parse(dataset.date)
        val date = SimpleDateFormat("EEEE dd MMMM", Locale.FRANCE).format(parsedDate)
        detailsDate.text = date.capitalize()

        detailsImage.setImageResource(Weather.getPictureByName(dataset.type))

        detailsActivity.setBackgroundColor(ContextCompat.getColor(this, Weather.getColorByType(dataset.type)))

        detailsTemp.text = dataset.temperatureMax.toString() + this.getString(R.string.degree)
        detailsTempMin2.text = dataset.temperatureMin.toString() + this.getString(R.string.degree)
        detailsTempMax2.text = dataset.temperatureMax.toString() + this.getString(R.string.degree)

        detailsUV2.text = dataset.uvIndex.toString()

        detailsVent2.text = dataset.windDirection + " / " + dataset.windSpeed.toString()
    }

}
