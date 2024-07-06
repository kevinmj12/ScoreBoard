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
        val redSetsScoreTextView: TextView = findViewById(R.id.textview_red_sets_score)
        val blueSetsScoreTextView: TextView = findViewById(R.id.textview_blue_sets_score)
        redScoreTextView.text = redScoreboard.score.toString()
        blueScoreTextView.text = blueScoreboard.score.toString()
        redSetsScoreTextView.text = redScoreboard.setsScore.toString()
        blueSetsScoreTextView.text = blueScoreboard.setsScore.toString()

        connectTextViewScore(this, redScoreboard, redScoreTextView)
        connectTextViewScore(this, blueScoreboard, blueScoreTextView)
        connectTextViewSetsScore(this, redScoreboard, redSetsScoreTextView)
        connectTextViewSetsScore(this, blueScoreboard, blueSetsScoreTextView)
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
        val SWIPE_VELOCITY_THRESHOLD = 30

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

@SuppressLint("ClickableViewAccessibility")
fun connectTextViewSetsScore(context: Context, scoreboard: Scoreboard, textView: TextView){
    val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            scoreboard.plusSetsScore()
            textView.text = scoreboard.setsScore.toString()
            return true
        }
        override fun onDoubleTap(e: MotionEvent): Boolean {
            scoreboard.minusSetsScore()
            textView.text = scoreboard.setsScore.toString()
            return true
        }

        val SWIPE_THRESHOLD = 30
        val SWIPE_VELOCITY_THRESHOLD = 30

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
                        scoreboard.minusSetsScore()
                        textView.text = scoreboard.setsScore.toString()
                    }
                    else{
                        scoreboard.plusSetsScore()
                        textView.text = scoreboard.setsScore.toString()
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