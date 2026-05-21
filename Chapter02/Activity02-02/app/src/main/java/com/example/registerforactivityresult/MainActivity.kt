package com.example.registerforactivityresult

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.registerforactivityresult.ui.theme.RegisterForActivityResultTheme

class MainActivity : ComponentActivity() {
    private var userName by mutableStateOf("")
    private var password by mutableStateOf("")

    private var isLoginValid by mutableStateOf(false)

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        val data = activityResult.data

        userName = data?.getStringExtra(
            USER_NAME_KEY
        ) ?: ""

        password = data?.getStringExtra(
            PASSWORD_KEY
        ) ?: ""

        isLoginValid = data?.getBooleanExtra(
            IS_LOGIN_VALID_KEY,
            false
        ) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterForActivityResultTheme {
                if (!isLoginValid) {
                    FormScreen()
                } else {
                    WelcomeScreen(userName)
                }
            }
        }
    }

    companion object {
        const val USER_NAME_KEY = "USER_NAME_KEY"
        const val PASSWORD_KEY = "PASSWORD_KEY"
        const val IS_LOGIN_VALID_KEY = "IS_LOGIN_VALID_KEY"
    }

    @Composable
    fun FormScreen(
        modifier: Modifier = Modifier
    ) {
        val context = LocalContext.current
        Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = {
                        Text(
                            text = stringResource(R.string.label_user_name)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(
                            text = stringResource(R.string.label_password)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Button(
                    onClick = {
                        val intent = Intent(
                            context,
                            LoginActivity::class.java
                        )
                        intent.putExtra(USER_NAME_KEY, userName)
                        intent.putExtra(PASSWORD_KEY, password)
                        startForResult.launch(intent)
                    }
                ) {
                    Text(
                        text = stringResource(R.string.button_login)
                    )
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(userName: String, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = stringResource(R.string.welcome_message, userName),
                fontSize = 28.sp
            )
        }
    }
}