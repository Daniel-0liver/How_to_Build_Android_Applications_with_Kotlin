package com.example.wheresmycar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.wheresmycar.ui.theme.WheresMyCarTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WheresMyCarTheme {
                WhereIsMyCarScreen()
            }
        }
    }
}

@Composable
fun WhereIsMyCarScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        var currentLocation by remember {
            mutableStateOf(
                LatLng(
                    0.0,
                    0.0
                )
            )
        }

        val cameraPositionState = rememberSaveable(
             currentLocation,
            saver = CameraPositionState.Saver
        ) {
            CameraPositionState(
                position = CameraPosition.fromLatLngZoom(
                    currentLocation,
                    10f
                )
            )
        }
        GoogleMap(
            modifier = Modifier
                .padding(innerPadding),
            cameraPositionState = cameraPositionState,
            onMapClick = {}
        ) {

        }
    }
}