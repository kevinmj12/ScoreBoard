package com.example.scoreboard

class ScoreBoard{
    var score: Int = 0
    var setsScore: Int = 0

    fun addScore() {
        this.score++
    }
    fun subtractScore() {
        this.score--
    }

    fun addSetsScore() {
        this.setsScore++
    }
    fun subtractSetsScore(){
        this.setsScore--
    }
}