package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var red by remember { mutableStateOf("") }
                    var green by remember { mutableStateOf("") }
                    var blue by remember { mutableStateOf("") }
                    var defaultColor by remember { mutableStateOf(Color.White) }
                    val context = LocalContext.current
                    var colorToShow = ""

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(stringResource(R.string.information_field))
                        OutlinedTextField(
                            value = red,
                            onValueChange = { red = it },
                            label = { Text("Red Channel") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = green,
                            onValueChange = { green = it },
                            label = { Text("Green Channel") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = blue,
                            onValueChange = { blue = it },
                            label = { Text("Blue Channel") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (isValidHexadecimal(red) &&
                                    isValidHexadecimal(green) &&
                                    isValidHexadecimal(blue)
                                ) {
                                    colorToShow = "#$red$green$blue"
                                    defaultColor = Color(colorToShow.toColorInt())
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Not valid hexadecimal",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        ) {
                            Text("Create RGB Color")
                        }
                        ElevatedCard(
                            colors = CardDefaults.cardColors(
                                containerColor = defaultColor
                            ),
                            modifier = Modifier
                                .size(width = 360.dp, height = 180.dp),
                        ) {
                        }
                    }
                }
            }
        }
    }

    fun isValidHexadecimal(input: String): Boolean {
        return input.filter {
            it in '0'..'9' ||
                    it in 'A'..'F' ||
                    it in 'a'..'f'
        }.length == 2
    }
}
