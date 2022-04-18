package com.example.tictactoy

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {
        val buSelected = view as Button

        var cellId = 0
        when (buSelected.id) {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }

//        Log.d("buClick", buSelected.id.toString())
//        Log.d("buClick : CellId: ", cellId.toString()
        playGame(cellId, buSelected)
    }

    var activePlayer = 1

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    fun playGame(cellId: Int, buSelected: Button) {
        if (activePlayer == 1) {
            var playtext: TextView? = findViewById(R.id.playerStatus)
            playtext?.text = "Your Turn"
            buSelected.text = "X"
            buSelected.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blue))
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        } else {
            var playtext: TextView? = findViewById(R.id.playerStatus)
            playtext?.text = "Computer's Turn"
            buSelected.text = "O"
            buSelected.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green))
            player2.add(cellId)
            activePlayer = 1
        }
        buSelected.isEnabled = false
        buSelected.textSize = 50f
        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1

        //cheching for row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //cheching for row 2
        if (player1.contains(3) && player1.contains(4) && player1.contains(5)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(4) && player2.contains(5)) {
            winner = 2
        }
        //cheching for row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //checking for col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //checking for col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //checking for col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        //checking for left diagonal
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        //checking for right diagonal
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }
        //show winner
        if (winner == 1) {
            var player1WinsCount = player1WinsCount + 1
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
            restartGame()
        } else if (winner == 2) {
            var player2WinsCount = player2WinsCount + 1
            Toast.makeText(this, "Computer win the game", Toast.LENGTH_LONG).show()
            restartGame()
        }

    }

    fun autoPlay() {
        var emptyCells = ArrayList<Int>()
        for (cellId in 1..9) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }

        if(emptyCells.size == 0){
            restartGame()
        }
        val randIndex = Random.nextInt(emptyCells.size)
        val cellId = emptyCells[randIndex]

        var buSelected: Button?
        buSelected = when (cellId) {
            1 -> R.id.button1
            2 -> R.id.button2
            3 -> R.id.button3
            4 -> R.id.button4
            5 -> R.id.button5
            6 -> R.id.button6
            7 -> R.id.button7
            8 -> R.id.button8
            9 -> R.id.button9
            else -> {R.id.button1}
        }
        playGame(cellId, buSelected)
    }

    var player1WinsCount = 0
    var player2WinsCount = 0
    fun restartGame() {
        activePlayer = 1
        player1.clear()
        player2.clear()

        for (cellId in 1..9) {
            var buSelected: Button? = when (cellId) {
                1 -> R.id.button1
                2 -> R.id.button2
                3 -> R.id.button3
                4 -> R.id.button4
                5 -> R.id.button5
                6 -> R.id.button6
                7 -> R.id.button7
                8 -> R.id.button8
                9 -> R.id.button9
                else -> {R.id.button1}
            }
            buSelected!!.text=""
            buSelected!!.setBackgroundResource(R.color.whiteBu)
            buSelected.isEnabled = true
        }
        Toast.makeText(this, "Player1 : $player1WinsCount || Computer : $player2WinsCount", Toast.LENGTH_LONG).show()
    }
}