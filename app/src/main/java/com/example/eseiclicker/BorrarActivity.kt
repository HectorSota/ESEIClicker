package com.example.eseiclicker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class BorrarActivity : ComponentActivity() {

    override fun onCreate(savedInstanteState: Bundle?) {
        super.onCreate(savedInstanteState)
        setContentView(R.layout.activity_confirmar_borrado)

        val deleteButton: Button = findViewById(R.id.borrarButton)
        val cancelButton: Button = findViewById(R.id.cancelarButton)

        deleteButton.setOnClickListener { onDeleteButtonClick() }
        cancelButton.setOnClickListener { onCancelButtonClick() }
    }

    private fun onDeleteButtonClick() {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt("currentScore", 0)
            putInt("autoClicksPerSecond", 0)
            apply()
        }

        val intent = Intent(this, CreateGameActivity::class.java)

        startActivity(intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

    private fun onCancelButtonClick(){
        finish()
    }
}