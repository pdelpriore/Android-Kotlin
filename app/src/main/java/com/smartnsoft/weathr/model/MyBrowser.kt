package com.smartnsoft.weathr.model

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MyBrowser : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

        return super.shouldOverrideUrlLoading(view, request)
    }
}