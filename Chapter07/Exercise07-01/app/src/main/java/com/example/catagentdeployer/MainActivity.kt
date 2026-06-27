package com.example.catagentdeployer

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.catagentdeployer.ui.theme.CatAgentDeployerTheme
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

class MainActivity : ComponentActivity() {
    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var currentUserLocation by remember {
                mutableStateOf(LatLng(0.0, 0.0))
            }

            fun getUserLocation() {
                val cancellationTokenSource = CancellationTokenSource()
                lifecycleScope.launch @SuppressLint("MissingPermission") {
                    suspendCancellableCoroutine { continuation ->
                        fusedLocationProviderClient.getCurrentLocation(
                            PRIORITY_HIGH_ACCURACY,
                            cancellationTokenSource.token
                        ).addOnSuccessListener { location: Location? ->
                            currentUserLocation = LatLng(
                                location?.latitude ?: 0.0,
                                location?.longitude ?: 0.0
                            )
                        }
                        continuation.invokeOnCancellation {
                            cancellationTokenSource.cancel()
                        }
                    }
                }
            }

            var locationPermissionsGranted by remember {
                mutableStateOf(areLocationPermissionsGranted())
            }
            var shouldShowLocationRationale by remember {
                mutableStateOf(false)
            }
            val requestLocationPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions(),
                onResult = { permissions ->
                    locationPermissionsGranted = permissions.values.all { it }
                    if (!locationPermissionsGranted) {
                        shouldShowLocationRationale = shouldShowLocationPermissionRationale()
                    } else {
                        getUserLocation()
                    }
                }
            )

            fun requestLocationPermission() {
                requestLocationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }

            CatAgentDeployerTheme {
                val snackbarHostState = remember {
                    SnackbarHostState()
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState
                        )
                    }
                ) { innerPadding ->
                    val scope = rememberCoroutineScope()
                    LaunchedEffect(
                        key1 = shouldShowLocationRationale,
                        block = {
                            if (shouldShowLocationRationale) {
                                scope.launch {
                                    val userAction = snackbarHostState.showSnackbar(
                                        message = "The app will not work without knowing your precise location",
                                        actionLabel = "Approve",
                                        duration = SnackbarDuration.Indefinite,
                                        withDismissAction = true
                                    )
                                    when (userAction) {
                                        SnackbarResult.ActionPerformed -> {
                                            requestLocationPermission()
                                        }

                                        SnackbarResult.Dismissed -> {}
                                    }
                                }
                            }
                        }
                    )
                    val cameraPositionState = rememberSaveable(
                        currentUserLocation,
                        saver = CameraPositionState.Saver
                    ) {
                        CameraPositionState(
                            position = CameraPosition.fromLatLngZoom(
                                currentUserLocation,
                                10f
                            )
                        )
                    }
                    GoogleMap(
                        modifier = Modifier.padding(innerPadding),
                        cameraPositionState = cameraPositionState
                    ) {
                        val markerState = rememberSaveable(
                            currentUserLocation,
                            saver = MarkerState.Saver
                        ) {
                            MarkerState(position = currentUserLocation)
                        }
                        if (currentUserLocation.latitude != 0.0 && currentUserLocation.longitude != 0.0) {
                            Marker(
                                state = markerState,
                                title = "You are here"
                            )
                        }
                    }
                    Button(
                        onClick = {
                            if (!locationPermissionsGranted) {
                                shouldShowLocationRationale =
                                    shouldShowLocationPermissionRationale()
                            } else {
                                getUserLocation()
                            }
                            if (!locationPermissionsGranted && !shouldShowLocationRationale) {
                                requestLocationPermission()
                            }
                        },
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text(text = "Get location ($currentUserLocation)")
                    }
                }
            }
        }
    }

    private fun areLocationPermissionsGranted(): Boolean =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun shouldShowLocationPermissionRationale() =
        shouldShowRequestPermissionRationale(
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) || shouldShowRequestPermissionRationale(
            Manifest.permission.ACCESS_FINE_LOCATION
        )


}

