package com.smartnsoft.weathr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val myDataSet = intent?.extras?.get("myDataSet") as Object

        Toast.makeText(this, myDataSet.toString(), Toast.LENGTH_SHORT).show()
    }
}
