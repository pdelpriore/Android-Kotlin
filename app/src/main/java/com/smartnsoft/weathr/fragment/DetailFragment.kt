package com.smartnsoft.weathr.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.smartnsoft.weathr.R

class DetailFragment : Fragment(), MenuFragment.FragmentMenuCityNameListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_detail, container, false)

        val cityDetail = rootView.findViewById<TextView>(R.id.cityDetail)

        cityDetail.text = "Ass"

        return rootView
    }

    override fun getCityName(cityName: String?) {


    }

}
