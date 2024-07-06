package com.example.scoreboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.gesture.Gesture
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View.OnClickListener
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

//        val redPlusButton: Button = findViewById(R.id.btn_red_plus_score)
//        val redMinusButton: Button = findViewById(R.id.btn_red_minus_score)
//        val bluePlusButton: Button = findViewById(R.id.btn_blue_plus_score)
//        val blueMinusButton: Button = findViewById(R.id.btn_blue_minus_score)
//
//        connectBtnScoreboard(redPlusButton, redScoreboard, redScoreTextView, true)
//        connectBtnScoreboard(redMinusButton, redScoreboard, redScoreTextView, false)
//        connectBtnScoreboard(bluePlusButton, blueScoreboard, blueScoreTextView, true)
//        connectBtnScoreboard(blueMinusButton, blueScoreboard, blueScoreTextView, false)

        connectTextViewScore(this, redScoreboard, redScoreTextView)
    }
}

@SuppressLint("ClickableViewAccessibility")
fun connectTextViewScore(context: Context, scoreboard: Scoreboard, textView: TextView){
    val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            scoreboard.plusScore()
            textView.text = scoreboard.score.toString()
            return true
        }
        override fun onDoubleTap(e: MotionEvent): Boolean {
            scoreboard.minusScore()
            textView.text = scoreboard.score.toString()
            return true
        }

        val SWIPE_THRESHOLD = 50
        val SWIPE_VELOCITY_THRESHOLD = 50

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1 != null){
                val diffY = e2.y - e1.y
                if (Math.abs(diffY) > SWIPE_THRESHOLD &&
                    Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD){
                    if (diffY > 0){
                        Log.d("log", "e2.x: " + e2.x.toString() + "/ e2.y: " + e2.y.toString())
                        Log.d("log", "e1.x: " + e1.x.toString() + "/ e1.y: " + e1.y.toString())
                        scoreboard.minusScore()
                        textView.text = scoreboard.score.toString()
                    }
                    else{
                        scoreboard.plusScore()
                        textView.text = scoreboard.score.toString()
                    }
                }
            }
            return true
        }
    })

    textView.setOnTouchListener { _, event ->
        gestureDetector.onTouchEvent(event)
        true
    }
}