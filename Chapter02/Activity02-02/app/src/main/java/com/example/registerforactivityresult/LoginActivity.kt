package com.example.registerforactivityresult

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.registerforactivityresult.MainActivity.Companion.IS_LOGIN_VALID_KEY
import com.example.registerforactivityresult.MainActivity.Companion.PASSWORD_KEY
import com.example.registerforactivityresult.MainActivity.Companion.USER_NAME_KEY
import com.example.registerforactivityresult.ui.theme.RegisterForActivityResultTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

    }

    fun setForm(
        userName: String,
        password: String,
        isValidLogin: Boolean
    ) {
        Intent().let { pickedFormIntent ->
            pickedFormIntent.putExtra(
                USER_NAME_KEY,
                userName
            )
            pickedFormIntent.putExtra(
                PASSWORD_KEY,
                password
            )
            pickedFormIntent.putExtra(
                IS_LOGIN_VALID_KEY,
                isValidLogin
            )
            setResult(
                RESULT_OK,
                pickedFormIntent
            )
            finish()
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


}
