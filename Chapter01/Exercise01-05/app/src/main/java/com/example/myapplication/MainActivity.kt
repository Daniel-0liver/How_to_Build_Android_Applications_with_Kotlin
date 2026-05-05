package com.example.myapplication

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var firstName by remember { mutableStateOf("") }
                    var lastName by remember { mutableStateOf("") }
                    var fullName by remember { mutableStateOf("") }
                    val welcomeMessage = stringResource(R.string.welcome_to_the_app)
                    val enterNameErrorMessage = stringResource(R.string.please_enter_a_name)

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = { Text(stringResource(R.string.first_name)) },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text(stringResource(R.string.last_name)) },
                            modifier = Modifier.fillMaxWidth()
                        )

                        val context = LocalContext.current
                        Button(
                            onClick = {
                                if (firstName.isNotBlank() && lastName.isNotBlank()) {
                                    fullName = "$firstName $lastName"
                                } else {
                                    val toast = Toast.makeText(
                                        context,
                                        enterNameErrorMessage,
                                        Toast.LENGTH_SHORT
                                    )
                                    toast.setGravity(Gravity.CENTER, 0, 0)
                                    toast.show()
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(stringResource(R.string.enter_button))
                        }

                        if (fullName.isNotBlank()) {
                            Text(
                                text = "$welcomeMessage $fullName!"
                            )
                        } else {
                            fullName = ""
                            val toast = Toast.makeText(
                                context,
                                enterNameErrorMessage,
                                Toast.LENGTH_SHORT
                            )
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()
                        }
                    }
                }
            }
        }
    }
}
