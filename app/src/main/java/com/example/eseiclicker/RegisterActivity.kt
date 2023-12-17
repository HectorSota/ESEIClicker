package com.example.eseiclicker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.edit



class RegisterActivity : ComponentActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val goToLogin: TextView = findViewById(R.id.goLogin)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegister = findViewById(R.id.buttonRegister)


        buttonRegister.setOnClickListener { onRegisterButtonClick(it) }

        goToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onRegisterButtonClick(view: View) {

        val username = editTextUsername.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        when {
            username.isEmpty() || password.isEmpty() -> {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            }
            isUsernameTaken(username) -> {
                Toast.makeText(this, "Username is already taken", Toast.LENGTH_SHORT).show()
            }
            else -> {
                saveUserToSharedPreferences(username, password)
                Toast.makeText(this, "Register successful", Toast.LENGTH_SHORT).show()

                //Después de un registro, debería redirigirse al login.
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }

    //Busca el nombre de usuario en la lista de usuarios registrados.
    private fun isUsernameTaken(username: String): Boolean {
        return getRegisteredUsernames().contains(username)
    }

    //Devuelve un listado de usuarios registrados. Será útil para comprobar
    //Si un nombre de usuario ya está en uso.
    private fun getRegisteredUsernames(): Set<String> {
        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet("registered_usernames", mutableSetOf()) ?: mutableSetOf()
    }

    //Guarda la información del usuario. No es necesario hacer comprobaciones de si el username está
    //Disponible porque sólo se llama después de llevar a cabo dichas comprobaciones en RegisterActivity.
    private fun saveUserToSharedPreferences(username: String, password: String) {
        val registeredUsernames = getRegisteredUsernames().toMutableSet()
        registeredUsernames.add(username)

        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putStringSet("registered_usernames", registeredUsernames)
            putString("$username" + "_password", password)
        }
    }
}