package com.example.wordle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var counter = 0
    var wordToGuess = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordList = FourLetterWordList()
        wordToGuess = wordList.getRandomFourLetterWord()

        val button = findViewById<Button>(R.id.button)
        val textView1 = findViewById<TextView>(R.id.Guess1)
        val textView2 = findViewById<TextView>(R.id.Output1)
        val textView3 = findViewById<TextView>(R.id.Guess2)
        val textView4 = findViewById<TextView>(R.id.Output2)
        val textView5 = findViewById<TextView>(R.id.Guess3)
        val textView6 = findViewById<TextView>(R.id.Output3)
        val textViewTheWord = findViewById<TextView>(R.id.textViewTheWord)
        textViewTheWord.text = wordToGuess
        textViewTheWord.visibility = View.GONE

        button.setOnClickListener {
            it.hideKeyboard()

            val simpleEditText = findViewById<EditText>(R.id.textInputEditText)
            val strValue = simpleEditText.text.toString().uppercase()

            val guessResult = checkGuess(strValue)

            counter++

            if(counter == 1){

                textView1.text = strValue
                textView2.text = guessResult

            }
            else if (counter == 2){

                textView3.text = strValue
                textView4.text = guessResult

            }
            else{

                textView5.text = strValue
                textView6.text = guessResult

                textViewTheWord.visibility = View.VISIBLE
                counter = 0
                Toast.makeText(it.context, "That's all the guesses, good game!", Toast.LENGTH_LONG).show()

            }

            simpleEditText.text.clear()

        }

    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}