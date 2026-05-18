package com.example.standardactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.standardactivity.MainActivity.Companion.PASSWORD_KEY
import com.example.standardactivity.MainActivity.Companion.USER_NAME_KEY
import com.example.standardactivity.ui.theme.StandardActivityTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StandardActivityTheme {
                StandardActivityScreen(intent)
            }
        }
    }
}

@Composable
fun StandardActivityScreen(intent: Intent, modifier: Modifier = Modifier) {
    val userName = intent.getStringExtra(USER_NAME_KEY) ?: ""
    val password = intent.getStringExtra(PASSWORD_KEY) ?: ""
    val isValidLogin = validateLogin(userName, password)
    val context = LocalContext.current
    val mainActivityIntent = Intent(context, MainActivity::class.java)

    if (isValidLogin) {
        Scaffold(
            modifier = modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = stringResource(R.string.welcome_message, userName)
                )
            }
        }
    } else {
        Toast.makeText(
            context,
            stringResource(R.string.invalid_login),
            Toast.LENGTH_LONG
        ).show()
        context.startActivity(mainActivityIntent)
        (context as Activity).finish()
    }
}

fun validateLogin(userName: String, password: String): Boolean {
    val listOfValidUserNames = listOf("admin", "user1", "user2", "user3")
    val listOfValidPasswords = listOf("admin123", "user1pass", "user2pass", "user3pass")
    if (userName in listOfValidUserNames && password in listOfValidPasswords) {
        return true
    }
    return false
}

@Preview
@Composable
private fun StandardActivityScreenPreview() {
    StandardActivityScreen(Intent())
}
