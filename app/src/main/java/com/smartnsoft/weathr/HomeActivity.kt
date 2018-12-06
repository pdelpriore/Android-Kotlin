package com.smartnsoft.weathr
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.smartnsoft.weathr.model.Data
import com.smartnsoft.weathr.util.FetchData
import com.smartnsoft.weathr.util.ListAdapter
import com.smartnsoft.weathr.util.Network
import com.smartnsoft.weathr.util.Preference
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar.title = ""

        setSupportActionBar(toolbar)

        if (Network.isNetworkAvailable(this) == false) {

            val noNetwork = getString(R.string.no_network)

            Toast.makeText(this, noNetwork, Toast.LENGTH_SHORT).show()

        } else {

            val cityPreference: String? = Preference.getCity(this)
            val forecastPreference: String? = Preference.getForecasts(this)

            this.onStartApp(cityPreference ?: "paris", forecastPreference ?: "10")
        }

        swipe.setOnRefreshListener {

            if (Network.isNetworkAvailable(this) == false) {

                val noNetwork = getString(R.string.no_network)

                Toast.makeText(this, noNetwork, Toast.LENGTH_SHORT).show()

                swipe.isRefreshing = false

            } else {

                val cityPreference: String? = Preference.getCity(this)
                val forecastPreference: String? = Preference.getForecasts(this)

                swipe.isRefreshing = true

                this.onStartApp(cityPreference ?: "paris", forecastPreference ?: "10")

                Toast.makeText(this, "Météo pour ${cityPreference?.capitalize()} est mise à jour", Toast.LENGTH_SHORT).show()

                swipe.isRefreshing = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            R.id.toolbarSettings -> openSettings()

            R.id.refresh -> {

                if (Network.isNetworkAvailable(this) == false) {

                    val noNetwork = getString(R.string.no_network)

                    Toast.makeText(this, noNetwork, Toast.LENGTH_SHORT).show()

                    swipe.isRefreshing = false

                } else {

                    val cityPreference: String? = Preference.getCity(this)
                    val forecastPreference: String? = Preference.getForecasts(this)

                    swipe.isRefreshing = true

                    this.onStartApp(cityPreference ?: "paris", forecastPreference ?: "10")

                    Toast.makeText(this, "Météo pour ${cityPreference?.capitalize()} est mise à jour", Toast.LENGTH_SHORT).show()

                    swipe.isRefreshing = false
                }
            }

            R.id.toolbarAbout -> about()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onStartApp(city: String, forecast: String) {

        FetchData.fetchDataApi(this, city, forecast, object : FetchData.DataFetchCallback {

            override fun onDataFetched(data: Data?) {

                toolbarCustomTitle.text = city.capitalize()

                val viewAdapter = ListAdapter(data, this@HomeActivity)
                val viewManager = LinearLayoutManager(this@HomeActivity)

                recycler_list_item.apply {

                    setHasFixedSize(true)

                    layoutManager = viewManager

                    adapter = viewAdapter
                }
            }
        })
    }

    private fun openSettings() {
        val builder: AlertDialog.Builder? = this.let {
            AlertDialog.Builder(it)
        }

        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
        builder?.setView(dialogView)

        builder?.setTitle(R.string.menu_settings)
        builder?.setPositiveButton("OK") { dialog, which ->

            val city = dialogView.findViewById<EditText>(R.id.inputCity).text.toString()
            val forecast = dialogView.findViewById<EditText>(R.id.inputNdj).text.toString().toIntOrNull()

            if (Network.isNetworkAvailable(this) == false) {

                val noNetwork = getString(R.string.no_network)

                Toast.makeText(this, noNetwork, Toast.LENGTH_SHORT).show()

            } else

                if (city.isEmpty()) {

                    val nomDeVille = this.getString(R.string.nomDeVille)

                    Toast.makeText(this, nomDeVille, Toast.LENGTH_SHORT).show()

                } else if (forecast == null) {

                    val nombreDeJours = getString(R.string.nombreDeJours)

                    Toast.makeText(this, nombreDeJours, Toast.LENGTH_SHORT).show()

                } else if (city == "paris" || city == "london" || city == "milan") {

                    if (forecast in 5..10) {

                        Preference.setCity(this, city)
                        Preference.setForecasts(this, forecast.toString())

                        FetchData.fetchDataApi(this, city, forecast.toString(), object : FetchData.DataFetchCallback {

                            override fun onDataFetched(data: Data?) {

                                toolbarCustomTitle.text = city.capitalize()

                                val viewAdapter = ListAdapter(data, this@HomeActivity)
                                val viewManager = LinearLayoutManager(this@HomeActivity)

                                recycler_list_item.apply {

                                    setHasFixedSize(true)

                                    layoutManager = viewManager

                                    adapter = viewAdapter
                                }
                            }
                        })

                    } else {

                        val textForecast = getString(R.string.forecast_error)
                        Toast.makeText(this, textForecast, Toast.LENGTH_SHORT).show()
                    }

                } else {

                    val textCity = getString(R.string.city_error, city.capitalize())
                    Toast.makeText(this, textCity, Toast.LENGTH_SHORT).show()
                }
        }

        builder?.create()?.show()
    }

    private fun about() {

        val intent = Intent(this, AboutActivity::class.java)

        startActivity(intent)
    }
}
