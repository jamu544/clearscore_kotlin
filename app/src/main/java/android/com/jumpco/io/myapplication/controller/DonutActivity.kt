package android.com.jumpco.io.myapplication.controller

import android.com.jumpco.io.myapplication.R
import android.com.jumpco.io.myapplication.model.ClearScore
import android.com.jumpco.io.myapplication.utilities.CLEAR_SCORE
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class DonutActivity : AppCompatActivity() {


    //lateinit var clearScore : ClearScore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val clearScore = intent.getParcelableExtra<ClearScore>(CLEAR_SCORE)

    //    if(clearScore != null) {


      //  calculatedScoreTextView.text = "${clearScore.score}/${clearScore.maxScore}"

   //     }

     //  val  progressBar = findViewById<ProgressBar>(R.id.stats_progressbar)
    //   val results = findViewById<TextView>(R.id.calculation)

        //get data from splashscreen
//        val bundle = intent.extras
//
//        // performing the safety null check
//        var score:Int? = null
//        var maxScore:Int? = null
//         score = intent.getIntExtra("Score",0)
//        maxScore = intent.getIntExtra("Max Score",0)
//
//        results.text = "$score/$maxScore"
//
//        progressBar.progress = calculateCreditScore( score, maxScore)

    }

   //calculate the percentage of the users's progress
    fun calculateCreditScore(score: Int, maxScoreValue: Int): Int {
        val d = score.toDouble() / maxScoreValue.toDouble()
        return (d * 100).toInt()
    }
}