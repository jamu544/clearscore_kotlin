package android.com.jumpco.io.myapplication.controller

import android.com.jumpco.io.myapplication.R
import android.com.jumpco.io.myapplication.model.ClearScoreDetails
import android.com.jumpco.io.myapplication.utilities.EXTRA_DETAILS
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_donut.*


class DonutActivity : AppCompatActivity(),View.OnClickListener {


    lateinit var progressBar : ProgressBar
    lateinit var clearScoreDetail: ClearScoreDetails

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_DETAILS, clearScoreDetail)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donut)

        clearScoreDetail = intent.getParcelableExtra<ClearScoreDetails>(EXTRA_DETAILS)!!
        progressBar = findViewById<ProgressBar>(R.id.stats_progressbar)
        calculatedScoreTextView.setOnClickListener(this)

        if(clearScoreDetail != null) {
            clientRefText.text ="Client Ref: ${clearScoreDetail.clientRef}"
            calculatedScoreTextView.text = "${clearScoreDetail.score}/${clearScoreDetail.maxScore}"
            progressBar.progress = calculateCreditScore(clearScoreDetail.score, clearScoreDetail.maxScore)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null){
            clearScoreDetail = savedInstanceState.getParcelable(EXTRA_DETAILS)!!

        }
    }
   //calculate the percentage of the users's progress
    fun calculateCreditScore(score: Int, maxScoreValue: Int): Int {
        val d = score.toDouble() / maxScoreValue.toDouble()
        return (d * 100).toInt()
    }

    override fun onClick(view: View?) {
        when(view?.getId()){
            R.id.calculatedScoreTextView -> {

                val detailsActivity = Intent(this, DetailsActivity::class.java)
                detailsActivity.putExtra(EXTRA_DETAILS,clearScoreDetail)

                startActivity(detailsActivity)
                Toast.makeText(this, "More details.", Toast.LENGTH_SHORT).show()

            }
        }
    }


}