package android.com.jumpco.io.myapplication

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val  progressBar = findViewById<ProgressBar>(R.id.stats_progressbar)
       val results = findViewById<TextView>(R.id.calculation)

        //get data from splashscreen
        val bundle = intent.extras

        // performing the safety null check
        var score:Int? = null
        var maxScore:Int? = null
         score = intent.getIntExtra("Score",0)
        maxScore = intent.getIntExtra("Max Score",0)

        results.text = "$score/$maxScore"

        progressBar.progress = calculateCreditScore( score, maxScore)

    }

   //calculate the percentage of the users's progress
    fun calculateCreditScore(score: Int, maxScoreValue: Int): Int {
        val d = score.toDouble() / maxScoreValue.toDouble()
        return (d * 100).toInt()
    }
}