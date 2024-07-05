package com.example.scoreboard

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    private val redScoreboard = Scoreboard()
    private val blueScoreboard = Scoreboard()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 화면 가로 모드 설정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // 화면 몰입 모드 설정
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // 상태 표시줄, 내비게이션 바 숨김
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        // 스와이프하여 잠깐 보이도록 설정
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContentView(R.layout.activity_main)

        val redScoreTextView: TextView = findViewById(R.id.score_red)
        val blueScoreTextView: TextView = findViewById(R.id.score_blue)
        redScoreTextView.text = redScoreboard.score.toString()
        blueScoreTextView.text = blueScoreboard.score.toString()

        val redPlusButton: Button = findViewById(R.id.btn_red_plus_score)
        val redMinusButton: Button = findViewById(R.id.btn_red_minus_score)
        val bluePlusButton: Button = findViewById(R.id.btn_blue_plus_score)
        val blueMinusButton: Button = findViewById(R.id.btn_blue_minus_score)

        connectBtnScoreboard(redPlusButton, redScoreboard, redScoreTextView, true)
        connectBtnScoreboard(redMinusButton, redScoreboard, redScoreTextView, false)
        connectBtnScoreboard(bluePlusButton, blueScoreboard, blueScoreTextView, true)
        connectBtnScoreboard(blueMinusButton, blueScoreboard, blueScoreTextView, false)
    }
}

fun connectBtnScoreboard(button: Button, scoreboard: Scoreboard, textView: TextView, isPlus: Boolean){
    if (isPlus){
        button.setOnClickListener{
            scoreboard.plusScore()
            textView.text = scoreboard.score.toString()
        }
    }
    else{
        button.setOnClickListener{
            scoreboard.minusScore()
            textView.text = scoreboard.score.toString()
        }
    }
}