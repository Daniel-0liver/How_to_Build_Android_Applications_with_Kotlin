package com.example.saveandrestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saveandrestore.ui.theme.SaveAndRestoreTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    var randomNumber by mutableIntStateOf(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if (savedInstanceState != null) {
                randomNumber = savedInstanceState.getInt(RANDOM_NUMBER, 0)
            }
            SaveAndRestoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                randomNumber = generateRandomNumber()
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.generate_random_number),
                                fontSize = 18.sp
                            )
                        }
                        Text(
                            text = stringResource(R.string.random_number_message, randomNumber),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RANDOM_NUMBER, randomNumber)
    }

    companion object {
        private const val RANDOM_NUMBER = "RANDOM_NUMBER"
    }
}

private fun generateRandomNumber(): Int {
    return Random.nextInt(0, 1000)
}
