package com.example.eseiclicker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class CreateGameActivity : ComponentActivity() {

    override fun onCreate(savedInstanteState: Bundle?) {
        super.onCreate(savedInstanteState)
        setContentView(R.layout.activity_create_game)

        val createGameButton: Button = findViewById(R.id.createGameButton)

        createGameButton.setOnClickListener { onCreateGameButtonClick(it) }
    }


    private fun onCreateGameButtonClick(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}