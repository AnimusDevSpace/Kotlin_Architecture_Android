package com.assignment.cleanarchitecture.ui.ui.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.assignment.cleanarchitecture.databinding.ActivityDetailsBinding
import com.assignment.cleanarchitecture.ui.ktx.viewBinding
import com.cleanarchitecture.network.utils.const.ConstantData

class DetailsActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityDetailsBinding::inflate)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url:String = intent.getStringExtra(ConstantData.URL_KEY).toString()

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
        binding.webView.loadUrl(url)

    }
}
