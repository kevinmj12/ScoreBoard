package com.example.scoreboard

class Scoreboard{
    var score: Int = 0
    var setsScore: Int = 0

    fun plusScore() {
        score++
    }
    fun minusScore() {
        score--
    }

    fun plusSetsScore() {
        setsScore++
    }
    fun minusSetsScore(){
        if (setsScore > 0) {
            setsScore--
        }
    }
}