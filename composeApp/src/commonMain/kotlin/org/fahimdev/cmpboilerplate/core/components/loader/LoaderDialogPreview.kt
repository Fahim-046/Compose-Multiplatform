package org.fahimdev.cmpboilerplate.core.components.loader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
private fun LoaderDialogPreview() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Bouncing Dot Loader")
            BouncingDotsLoader()
            Text(text = "Breathing Dot Loader")
            BreathingDotsLoader()
            Text(text = "Gradient Spinner Loader")
            GradientSpinnerLoader()
            Text(text = "Orbit Loader")
            OrbitLoader()
            Text(text = "Pulse Ring Loader")
            PulseRingLoader()
            Text(text = "Wave Loader")
            WaveLoader()
        }
    }
}