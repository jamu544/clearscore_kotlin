package android.com.jumpco.io.myapplication.controller


import android.com.jumpco.io.myapplication.databinding.ActivityDetailsBinding
import android.com.jumpco.io.myapplication.model.ClearScoreDetails
import android.com.jumpco.io.myapplication.utilities.EXTRA_DETAILS
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding  //defining the binding class


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root) // we now set the contentview as the binding.root


        val detail = intent.getParcelableExtra<ClearScoreDetails>(EXTRA_DETAILS)

        if(detail != null){

            binding.detailsClearscoreText.text = "Client REF: ${detail.clientRef} \n" +
                    "Account ID Status : ${detail.status} \n" +
                    "Credit Report info : ${detail.score} / ${detail.maxScore} \n" +
                    "Personal Type : ${detail.personaType} \n" +
                    "Dash Status : ${detail.dashboardStatus}"
        }
    }
}