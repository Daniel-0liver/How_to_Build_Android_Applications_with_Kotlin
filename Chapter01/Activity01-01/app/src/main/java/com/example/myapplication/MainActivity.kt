package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
                    val red = rememberTextFieldState()
                    val green = rememberTextFieldState()
                    val blue = rememberTextFieldState()
                    var defaultColor by remember { mutableStateOf(Color.White) }

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
                            state = red,
                            label = { Text(stringResource(R.string.red_channel)) },
                            isError = !isValidHexadecimal(red),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            state = green,
                            label = { Text(stringResource(R.string.green_channel)) },
                            isError = !isValidHexadecimal(green),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            state = blue,
                            label = { Text(stringResource(R.string.blue_channel)) },
                            isError = !isValidHexadecimal(blue),
                            modifier = Modifier.fillMaxWidth()
                        )

                        val isValidInput =
                            isValidHexadecimal(red) && isValidHexadecimal(green) && isValidHexadecimal(
                                blue
                            )
                        val colorToShow = "#${red.text}${green.text}${blue.text}"

                        Text(
                            text = colorToShow,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        if (isValidInput) {
                            defaultColor = Color(colorToShow.toColorInt())
                            ElevatedCard(
                                colors = CardDefaults.cardColors(
                                    containerColor = defaultColor
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                            ) {
                            }
                        }
                    }
                }
            }
        }
    }

    private fun isValidHexadecimal(input: TextFieldState): Boolean {
        val inputText = input.text
        return inputText.length == 2 && inputText.all {
            it in '0'..'9' ||
                    it in 'A'..'F' ||
                    it in 'a'..'f'
        }
    }
}
