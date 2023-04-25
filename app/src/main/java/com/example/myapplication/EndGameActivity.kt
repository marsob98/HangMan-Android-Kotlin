package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EndGameActivity : AppCompatActivity() {

    private lateinit var endGameTextView: TextView
    private lateinit var restartButton: Button
    private lateinit var quitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)

        endGameTextView = findViewById(R.id.endGameTextView)
        restartButton = findViewById(R.id.restartButton)
        quitButton = findViewById(R.id.quitButton)

        val won = intent.getBooleanExtra("won", false)
        endGameTextView.text = if (won) "Wygrałeś!" else "Przegrałeś!"

        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        quitButton.setOnClickListener {
            finishAffinity()
        }
    }
}
