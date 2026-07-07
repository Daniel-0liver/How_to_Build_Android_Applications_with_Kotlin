package com.example.wheresmycar

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.DrawableCompat.setTint
import androidx.lifecycle.lifecycleScope
import com.example.wheresmycar.ui.theme.WheresMyCarTheme
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
        MapsInitializer.initialize(this)
        val preferencesManager = PreferencesManager(this)
        enableEdgeToEdge()
        setContent {
            var currentLocation by remember {
                mutableStateOf(
                    preferencesManager.loadLocation() ?: LatLng(0.0, 0.0)
                )
            }

            fun getUserLocation() {
                val cancellationTokenSource = CancellationTokenSource()
                lifecycleScope.launch @SuppressLint("MissingPermission") {
                    suspendCancellableCoroutine { continuation ->
                        fusedLocationProviderClient.getCurrentLocation(
                            PRIORITY_HIGH_ACCURACY,
                            cancellationTokenSource.token
                        ).addOnSuccessListener { location: Location? ->
                            val newLocation = LatLng(
                                location?.latitude ?: 0.0,
                                location?.longitude ?: 0.0
                            )
                            currentLocation = newLocation
                            preferencesManager.saveLocation(newLocation)
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
            WheresMyCarTheme {
                val snackBarHostState = remember {
                    SnackbarHostState()
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackBarHostState,
                        )
                    }
                ) { innerPadding ->
                    val scope = rememberCoroutineScope()
                    LaunchedEffect(
                        key1 = shouldShowLocationRationale,
                        block = {
                            if (shouldShowLocationRationale) {
                                scope.launch {
                                    val userAction = snackBarHostState.showSnackbar(
                                        message = "Location permissions are required for this feature.",
                                        actionLabel = "Approve",
                                        duration = SnackbarDuration.Indefinite,
                                        withDismissAction = true
                                    )
                                    when (userAction) {
                                        SnackbarResult.ActionPerformed -> {
                                            requestLocationPermission()
                                        }

                                        SnackbarResult.Dismissed -> {
                                            shouldShowLocationRationale = false
                                        }
                                    }
                                }
                            }
                        }
                    )

                    Box(modifier = Modifier.padding(innerPadding)) {
                        val markerIcon by remember {
                            mutableStateOf(
                                getBitmapDescriptorFromVector(
                                    R.drawable.car_icon
                                )
                            )
                        }
                        WhereIsMyCarScreen(
                            currentLocation = currentLocation,
                            markerIcon = markerIcon,
                            onMarkerPositionChanged = { newLocation ->
                                currentLocation = newLocation
                                preferencesManager.saveLocation(newLocation)
                            },
                            modifier = Modifier.fillMaxSize()
                        )

                        Button(
                            onClick = {
                                if (!locationPermissionsGranted) {
                                    shouldShowLocationRationale =
                                        shouldShowLocationPermissionRationale()
                                } else {
                                    getUserLocation()
                                }
                                if (!locationPermissionsGranted &&
                                    !shouldShowLocationRationale
                                ) {
                                    requestLocationPermission()
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(16.dp)
                        ) {
                            Text(text = "Current location $currentLocation")
                        }
                    }
                }
            }
        }
    }

    private fun areLocationPermissionsGranted() =
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

    private fun getBitmapDescriptorFromVector(
        @DrawableRes vectorDrawableResourceId: Int
    ): BitmapDescriptor? = ContextCompat.getDrawable(
        this,
        vectorDrawableResourceId
    )?.let { vectorDrawable ->
        vectorDrawable.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val drawableWithTint = DrawableCompat.wrap(vectorDrawable)
        setTint(
            drawableWithTint,
            Color.DKGRAY
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawableWithTint.draw(canvas)
        BitmapDescriptorFactory.fromBitmap(bitmap)
            .also { bitmap.recycle() }
    }
}

@Composable
fun WhereIsMyCarScreen(
    currentLocation: LatLng,
    markerIcon: BitmapDescriptor?,
    onMarkerPositionChanged: (LatLng) -> Unit,
    modifier: Modifier = Modifier
) {
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

    val markerState = rememberSaveable(
        currentLocation,
        saver = MarkerState.Saver
    ) {
        MarkerState(position = currentLocation)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            markerState.position = latLng
            onMarkerPositionChanged(latLng)
        }
    ) {
        if (currentLocation != LatLng(0.0, 0.0)) {
            Marker(
                state = markerState,
                title = "${currentLocation.latitude}, ${currentLocation.longitude}",
                icon = markerIcon
            )
        }
    }
}