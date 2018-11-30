package com.smartnsoft.weathr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.smartnsoft.weathr.fragment.MenuFragment
import com.smartnsoft.weathr.model.Data
import com.smartnsoft.weathr.util.ListAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), MenuFragment.FragmentMenuListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

    override fun getDataApi(dataApi: Data?) {

        val viewAdapter = ListAdapter(dataApi?.forecasts ?: ArrayList(), this)
        val viewManager = LinearLayoutManager(this)

        recycler_list_item.apply {

            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }
    }
}
