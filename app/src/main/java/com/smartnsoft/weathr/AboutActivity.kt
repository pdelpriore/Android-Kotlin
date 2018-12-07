package com.smartnsoft.weathr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbarAbout)

        toolbarAbout.logo = this.getDrawable(R.drawable.logo_weathr)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val toolbarSetting = menu?.findItem(R.id.toolbarSettings)
        val toolbarAbout = menu?.findItem(R.id.toolbarAbout)
        val refresh = menu?.findItem(R.id.refresh)

        toolbarSetting?.setVisible(false)
        toolbarAbout?.setVisible(false)
        refresh?.setVisible(false)

        return super.onCreateOptionsMenu(menu)
    }
}
