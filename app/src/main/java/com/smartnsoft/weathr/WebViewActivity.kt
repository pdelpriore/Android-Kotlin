package com.smartnsoft.weathr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.smartnsoft.weathr.model.MyBrowser
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webview.webViewClient = MyBrowser()
        webview.loadUrl("http://www.smartnsoft.com")
        webview.settings.javaScriptEnabled = true
    }
}
