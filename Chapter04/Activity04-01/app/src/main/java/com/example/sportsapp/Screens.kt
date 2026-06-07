package com.example.sportsapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun MySportsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    navController.navigate(
                        Destination.SelectedSport(
                            "Football"
                        )
                    )
                }
        ) {
            Text(
                text = "Football",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 24.sp
            )
        }
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    navController.navigate(
                        Destination.SelectedSport(
                            "Basketball"
                        )
                    )
                }
        ) {
            Text(
                text = "Basketball",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 24.sp
            )
        }
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    navController.navigate(
                        Destination.SelectedSport(
                            "Tennis"
                        )
                    )
                }
        ) {
            Text(
                text = "Tennis",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun SelectedSportScreen(
    sportName: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = sportName,
            fontSize = 28.sp
        )
    }
}

@Preview
@Composable
private fun MySportsScreenPreview() {
    val navController = rememberNavController()
    MySportsScreen(navController)
}