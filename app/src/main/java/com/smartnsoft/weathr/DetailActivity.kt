package com.smartnsoft.weathr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import com.smartnsoft.weathr.model.Forecast
import com.smartnsoft.weathr.model.Weather
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_home.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val cityExtra = intent.getStringExtra("CityExtra")

        toolbarDetailCustomTitle.text = cityExtra.capitalize()

        setSupportActionBar(toolbarDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val dataset : Forecast = intent.getSerializableExtra("ForecastExtra") as Forecast

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate = dateFormat.parse(dataset.date)
        val date = SimpleDateFormat("EEEE dd MMMM", Locale.FRANCE).format(parsedDate)
        detailsDate.text = date.capitalize()

        detailsImage.setImageResource(Weather.getPictureByName(dataset.type))

        detailsActivity.setBackgroundColor(ContextCompat.getColor(this, Weather.getColorByType(dataset.type)))

        detailsTemp.text = this.getString(R.string.tempMax, dataset.temperatureMax)
        detailsTempMin2.text = this.getString(R.string.tempMin2, dataset.temperatureMin)
        detailsTempMax2.text = this.getString(R.string.tempMax2, dataset.temperatureMax)

        detailsUV2.text = dataset.uvIndex.toString()

        detailsVent2.text = this.getString(R.string.wind, dataset.windDirection, dataset.windSpeed)
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar, menu)

        val toolbarSettings = menu?.findItem(R.id.toolbarSettings)

        toolbarSettings?.setVisible(false)

        return super.onCreateOptionsMenu(menu)
    }

}
