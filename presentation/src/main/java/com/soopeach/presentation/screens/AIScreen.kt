package com.soopeach.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun AIScreen(
    navController: NavController
) {
    val webViewState =
        rememberWebViewState(
            url = "http://codebox123.i234.me:7004/",
            additionalHttpHeaders = emptyMap()
        )
    val webViewClient = AccompanistWebViewClient()
    val webChromeClient = AccompanistWebChromeClient()

    WebView(
        state = webViewState,
        client = webViewClient,
        chromeClient = webChromeClient,
        onCreated = { webView ->
            with(webView) {
                settings.run {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    javaScriptCanOpenWindowsAutomatically = false
                }
            }
        }
    )
}