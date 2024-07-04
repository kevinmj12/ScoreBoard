package com.example.scoreboard

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.scoreboard.ui.theme.ScoreboardTheme

class MainActivity : ComponentActivity() {

    private val redScoreBoard = ScoreBoard()
    private val blueScoreBoard = ScoreBoard()

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

        val redScore = findViewById<TextView>(R.id.score_red)
        val blueScore = findViewById<TextView>(R.id.score_blue)
        redScore.text = redScoreBoard.score.toString()
        blueScore.text = blueScoreBoard.score.toString()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScoreboardTheme {
        Greeting("Android")
    }
}