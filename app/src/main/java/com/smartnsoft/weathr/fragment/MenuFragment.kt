package com.smartnsoft.weathr.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.smartnsoft.weathr.R
import com.smartnsoft.weathr.model.Data
import com.smartnsoft.weathr.util.FetchData
import com.smartnsoft.weathr.util.Network
import java.lang.RuntimeException

class MenuFragment : Fragment() {

    var listener: FragmentMenuListener? = null

    interface FragmentMenuListener {

        fun getDataApi(dataWthrApi: Data?)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_menu, container, false)

        val settingsButton = rootView.findViewById<View>(R.id.menu_settings)

        settingsButton.setOnClickListener { v ->
            val builder: AlertDialog.Builder? = activity?.let {
                AlertDialog.Builder(it)
            }

            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
            builder?.setView(dialogView)

            builder?.setTitle(R.string.menu_settings)
            builder?.setPositiveButton("OK") { dialog, which ->

                val city = dialogView.findViewById<EditText>(R.id.inputCity).text.toString()
                val forecast = dialogView.findViewById<EditText>(R.id.inputNdj).text.toString().toIntOrNull()

                if (Network.isNetworkAvailable(requireContext()) == false) {

                    val noNetwork = context?.getString(R.string.no_network)

                    Toast.makeText(requireContext(), noNetwork, Toast.LENGTH_SHORT).show()

                } else

                    if (city.isEmpty()) {

                        val nomDeVille = context?.getString(R.string.nomDeVille)

                        Toast.makeText(requireContext(), nomDeVille, Toast.LENGTH_SHORT).show()

                    } else if (forecast == null) {

                        val nombreDeJours = context?.getString(R.string.nombreDeJours)

                        Toast.makeText(requireContext(), nombreDeJours, Toast.LENGTH_SHORT).show()

                    } else if (city == "paris" || city == "london" || city == "milan") {

                        if (forecast in 5..10) {

                            var dataWthrApi: Data?

                            FetchData.fetchDataApi(requireContext(), city, forecast.toString(), object : FetchData.DataFetchCallback {

                                override fun onDataFetched(data: Data?) {

                                    dataWthrApi = data

                                    val ville = rootView.findViewById<TextView>(R.id.design_menu_item_text)

                                    ville.text = city.capitalize()

                                    dataWthrApi?.apply {
                                        listener?.getDataApi(this)
                                    }
                                }
                            })

                        } else {

                            val textForecast = context?.getString(R.string.forecast_error, forecast.toString())
                            Toast.makeText(context, textForecast, Toast.LENGTH_SHORT).show()
                        }

                    } else {

                        val textCity = context?.getString(R.string.city_error, city.capitalize())
                        Toast.makeText(context, textCity, Toast.LENGTH_SHORT).show()
                    }
            }
            builder?.create()?.show()
        }

        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is FragmentMenuListener) {

            listener = context

        } else {

            throw RuntimeException(context.toString() + "devrait implementer MenuFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }
}
