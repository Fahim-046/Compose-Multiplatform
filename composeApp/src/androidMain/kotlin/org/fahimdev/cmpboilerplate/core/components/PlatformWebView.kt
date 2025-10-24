package org.fahimdev.cmpboilerplate.core.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import org.fahimdev.cmpboilerplate.core.components.loader.BouncingDotsLoader

@SuppressLint("SetJavaScriptEnabled")
@Composable
actual fun PlatformWebView(
    navController: NavController,
    url: String,
    modifier: Modifier,
    enableJavaScript: Boolean
) {
    var isLoading by remember { mutableStateOf(true) }

    val videoId = remember(url) { url.substringAfter("embed/").substringBefore("?") }

    val html = """
        <!DOCTYPE html>
        <html>
        <head>
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <style>
            html, body {
              margin: 0;
              padding: 0;
              height: 100%;
              background-color: black;
            }
            iframe {
              width: 100%;
              height: 100%;
              border: none;
            }
          </style>
        </head>
        <body>
          <iframe 
            src="https://www.youtube.com/embed/$videoId?autoplay=1&playsinline=1&modestbranding=1&rel=0"
            allow="autoplay; encrypted-media; fullscreen; picture-in-picture"
            allowfullscreen>
          </iframe>
        </body>
        </html>
    """.trimIndent()

    Box(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                FrameLayout(context).apply {
                    val webView = WebView(context).apply {
                        setBackgroundColor(0x00000000)
                        settings.apply {
                            javaScriptEnabled = enableJavaScript
                            domStorageEnabled = true
                            mediaPlaybackRequiresUserGesture = false
                            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            loadWithOverviewMode = true
                            useWideViewPort = true
                            userAgentString = userAgentString.replace("; wv", "")
                        }

                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                isLoading = true
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                isLoading = false
                            }
                        }

                        webChromeClient = object : WebChromeClient() {
                            private var customView: View? = null
                            private var customViewCallback: CustomViewCallback? = null

                            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                                if (customView != null) {
                                    callback?.onCustomViewHidden()
                                    return
                                }
                                customView = view
                                customViewCallback = callback
                                this@apply.visibility = View.GONE
                                this@apply.addView(view)
                            }

                            override fun onHideCustomView() {
                                customView?.let {
                                    this@apply.removeView(it)
                                    customView = null
                                    this@apply.visibility = View.VISIBLE
                                    customViewCallback?.onCustomViewHidden()
                                }
                            }
                        }

                        // ✅ Enable hardware acceleration (don’t disable!)
                        setLayerType(View.LAYER_TYPE_HARDWARE, null)

                        loadDataWithBaseURL(
                            "https://www.youtube.com",
                            html,
                            "text/html",
                            "UTF-8",
                            null
                        )
                    }

                    addView(webView, FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    ))
                }
            }
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BouncingDotsLoader()
            }
        }
    }
}