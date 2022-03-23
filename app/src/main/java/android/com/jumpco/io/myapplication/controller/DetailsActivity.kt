package android.com.jumpco.io.myapplication.controller

import android.com.jumpco.io.myapplication.R
import android.com.jumpco.io.myapplication.model.ClearScoreDetails
import android.com.jumpco.io.myapplication.utilities.EXTRA_DETAILS
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val detail = intent.getParcelableExtra<ClearScoreDetails>(EXTRA_DETAILS)

        if(detail != null){

            detailsClearscoreText.text = "Client REF: ${detail.clientRef} \n" +
                    "Account ID Status : ${detail.status} \n" +
                    "Credit Report info : ${detail.score} / ${detail.maxScore} \n" +
                    "Personal Type : ${detail.personaType} \n" +
                    "Dash Status : ${detail.dashboardStatus}"
        }
    }
}