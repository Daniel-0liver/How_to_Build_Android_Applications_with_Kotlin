package com.example.standardactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.standardactivity.MainActivity.Companion.PASSWORD_KEY
import com.example.standardactivity.MainActivity.Companion.USER_NAME_KEY
import com.example.standardactivity.ui.theme.StandardActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StandardActivityTheme {
                StandardScreen()
            }
        }
    }

    companion object {
        const val USER_NAME_KEY = "USER_NAME_KEY"
        const val PASSWORD_KEY = "PASSWORD_KEY"
    }
}

@Composable
fun StandardScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val loginIntent = Intent(context, LoginActivity::class.java)

    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        val userName = rememberTextFieldState()
        val password = rememberTextFieldState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                state = userName,
                label = {
                    Text(stringResource(R.string.label_user_name))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                state = password,
                label = {
                    Text(stringResource(R.string.label_password))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Button(
                onClick = {
                    if (userName.text.isNotBlank() && password.text.isNotBlank()) {
                        loginIntent.putExtra(USER_NAME_KEY, userName.text)
                        loginIntent.putExtra(PASSWORD_KEY, password.text)
                        context.startActivity(loginIntent)
                        (context as Activity).finish()
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.button_login)
                )
            }
        }
    }
}

@Preview
@Composable
private fun StandardScreenPreview() {
    StandardScreen()
}