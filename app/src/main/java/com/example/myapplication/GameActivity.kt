package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    private lateinit var hangmanImage: ImageView
    private lateinit var wordToGuess: TextView
    private lateinit var guessInput: EditText
    private lateinit var guessButton: Button
    private lateinit var guessedLetters: TextView

    private val hangmanImages = listOf(
        R.drawable.hangman_initial,
        R.drawable._1,
        R.drawable._2,
        R.drawable._3,
        R.drawable._4,
        R.drawable._5,
        R.drawable._6,
        R.drawable._7,
        R.drawable._8,
        R.drawable._9,
        R.drawable._10
    )

    private var incorrectGuesses = 0
    private lateinit var chosenWord: String
    private val guessedChars = mutableListOf<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        hangmanImage = findViewById(R.id.hangmanImage)
        wordToGuess = findViewById(R.id.wordToGuess)
        guessInput = findViewById(R.id.guessInput)
        guessButton = findViewById(R.id.guessButton)
        guessedLetters = findViewById(R.id.guessedLetters)

        chosenWord = chooseWord()
        wordToGuess.text = maskWord(chosenWord)

        guessButton.setOnClickListener {
            val input = guessInput.text.toString()
            if (input.isNotEmpty()) {
                val char = input[0].uppercaseChar()
                if (!guessedChars.contains(char)) {
                    guessedChars.add(char)
                    updateGuessedLetters()

                    if (char in chosenWord) {
                        updateWordToGuess()
                    } else {
                        incorrectGuesses++
                        hangmanImage.setImageResource(hangmanImages[incorrectGuesses])
                    }

                    if (incorrectGuesses == hangmanImages.lastIndex) {
                        endGame(false)
                    } else if (!wordToGuess.text.contains("_")) {
                        endGame(true)
                    }
                }
                guessInput.setText("")
            }
        }
    }

    private fun chooseWord(): String {
        val easyWords = listOf("SLON", "KROKODYL", "KOT", "PIES", "KWIAT", "DOM")
        val mediumWords = listOf("KANGUR", "HIPPOPOTAMUS", "KAMELEON", "PRZEDSIĘWZIĘCIE", "WYSPORTOWANY", "NAPROMIENIOWANY")
        val hardWords = listOf("ARCHAEOPTERYX", "BRACHIOSAURUS", "TYRANNOZAUR", "FENOLOFTALEINA")

        val difficulty = intent.getIntExtra("difficulty", 0)

        return when (difficulty) {
            1 -> easyWords.random()
            2 -> mediumWords.random()
            3 -> hardWords.random()
            else -> throw IllegalArgumentException("Nieznany poziom trudności: $difficulty")
        }
    }


    private fun maskWord(word: String): String {
        return word.map { if (it == ' ') ' ' else '_' }.joinToString(" ")
    }

    private fun updateWordToGuess() {
        val maskedWord = chosenWord.map { if (it in guessedChars) it else '_' }.joinToString(" ")
        wordToGuess.text = maskedWord
    }

    private fun updateGuessedLetters() {
        guessedLetters.text = "Odgadnięte litery: ${guessedChars.joinToString(", ")}"
    }

    private fun endGame(won: Boolean) {
        val intent = Intent(this, EndGameActivity::class.java)
        intent.putExtra("won", won)
        startActivity(intent)
        finish()
    }
}
