package com.example.eseiclicker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val goToRegister: TextView = findViewById(R.id.goRegister)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener { onLoginButtonClick(it) }

        goToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun onLoginButtonClick(view: View) {
        val intent = Intent(this, CreateGameActivity::class.java)
        val username = editTextUsername.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        when {
            username.isEmpty() || password.isEmpty() -> {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT)
                    .show()
            }

            !isUserAuthenticated(username, password) -> {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }

            else -> {
                //Se abre la actividad principal después de un login exitoso.
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    //Comprueba la existencia del nombre de usuario y la coincidencia de contraseña.
    private fun isUserAuthenticated(username: String, password: String): Boolean {
        val registeredUsernames = getRegisteredUsernames()
        return registeredUsernames.contains(username) && getPasswordForUsername(username) == password
    }

    //Devuelve un listado de usuarios registrados y sus contraseñas.
    private fun getRegisteredUsernames(): Set<String> {
        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        return sharedPreferences.getStringSet("registered_usernames", mutableSetOf()) ?: mutableSetOf()
    }

    //Devuelve la contraseña del usuario indicado.
    private fun getPasswordForUsername(username: String): String? {
        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        return sharedPreferences.getString("$username" + "_password", null)
    }
}