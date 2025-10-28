package org.fahimdev.cmpboilerplate.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCSignatureOverride
import kotlinx.cinterop.readValue
import org.fahimdev.cmpboilerplate.core.components.loader.BouncingDotsLoader
import org.fahimdev.cmpboilerplate.core.components.topbar.PrimaryTopBar
import platform.CoreGraphics.CGRectZero
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformWebView(
    navController: NavController,
    url: String,
    modifier: Modifier,
    enableJavaScript: Boolean
) {
    var isLoading by remember { mutableStateOf(true) }

    Column(modifier = modifier.fillMaxSize()) {
        PrimaryTopBar(
            title = url,
        )

        Box(modifier = Modifier.fillMaxSize()) {
            UIKitView(
                factory = {
                    val config = WKWebViewConfiguration().apply {
                        preferences.javaScriptEnabled = enableJavaScript
                    }
                    WKWebView(frame = CGRectZero.readValue(), configuration = config).apply {
                        navigationDelegate = object : NSObject(), WKNavigationDelegateProtocol {

                            @ObjCSignatureOverride
                            override fun webView(webView: WKWebView, didStartProvisionalNavigation: WKNavigation?) {
                                isLoading = true
                            }

                            @ObjCSignatureOverride
                            override fun webView(webView: WKWebView, didFinishNavigation: WKNavigation?) {
                                isLoading = false
                            }
                        }
                        loadRequest(NSURLRequest(NSURL(string = url)))
                    }
                },
                modifier = Modifier.fillMaxSize()
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
}