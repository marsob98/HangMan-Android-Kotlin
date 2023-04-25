package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var easyButton: Button
    private lateinit var mediumButton: Button
    private lateinit var hardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        easyButton = findViewById(R.id.easyButton)
        mediumButton = findViewById(R.id.mediumButton)
        hardButton = findViewById(R.id.hardButton)

        easyButton.setOnClickListener {
            startGame(Difficulty.EASY)
        }

        mediumButton.setOnClickListener {
            startGame(Difficulty.MEDIUM)
        }

        hardButton.setOnClickListener {
            startGame(Difficulty.HARD)
        }
    }

    private fun startGame(difficulty: Difficulty) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("difficulty", difficulty.ordinal + 1) // Dodaj +1, ponieważ wartość ordinal zaczyna się od 0
        startActivity(intent)
    }

    enum class Difficulty {
        EASY, MEDIUM, HARD
    }
}
