package android.com.jumpco.io.myapplication.controller

import android.com.jumpco.io.myapplication.R
import android.com.jumpco.io.myapplication.interfaces.ClearScoreService
import android.com.jumpco.io.myapplication.model.ClearScore
import android.com.jumpco.io.myapplication.utilities.*
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.os.Handler as Handler1

class SplashActivity : AppCompatActivity() {

  // var clearScore = ClearScore("", ClearScore.Score(0,0))

    lateinit var clearScore : ClearScore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hiding title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)

        if (isNetWorkConnected()){
            getClearScoreInformation()
            loadSplashScreen()
        }
        else {
            showNoInternetDialog()
        }
    }

    //display splashscreen and load the next activity
    private fun loadSplashScreen(){
        Handler1().postDelayed({

            val donutActivity = Intent(this, DonutActivity::class.java)
         //   donutActivity.putExtra(CLEAR_SCORE,clearScore)
            startActivity(donutActivity)
            finish()
        }, SPLASH_TIME_OUT)
    }

    //perform api call
    private fun getClearScoreInformation(){
        val retrofit = Retrofit.Builder()
            .baseUrl(ENDPOINT_JSON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ClearScoreService::class.java)
        val call = service.getClearScoreInfo()
        call.enqueue(object : Callback<ClearScore> {
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                call: Call<ClearScore>?,
                response: Response<ClearScore>?
            ) {
                if(response?.isSuccessful == true) {

                    val scoreResponse = response?.body()
                    if (scoreResponse != null) {

//                        clearScore = ClearScore(scoreResponse.accountIDVStatus.toString(),)
//                        clearScore.accountIDVStatus = scoreResponse.accountIDVStatus
//                        clearScore.scoreModel.maxScoreValue = scoreResponse.maxScore
//
                        println("json results  ${scoreResponse.accountIDVStatus.toString()}")
                        println("json results  ${scoreResponse.scoreModel}")
//
//                        println("Your score ${clearScore.score}")
//                        println("MAx is ${clearScore.maxScore}")
                    }



                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<ClearScore>?, t: Throwable?) {
                if (t != null) {
                    Toast.makeText(this@SplashActivity,"${t.message}",Toast.LENGTH_SHORT)
                }
            }


        })
    }


     // check internet connection
     private fun isNetWorkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null){
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if(capabilities != null){
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.i(TAG, INTERNET_CELLULAR)
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        Log.i(TAG, INTERNET_WIFI)
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i(TAG, INTERNET_ETHERNET)
                        return true
                    }
                }

            }
        }
        return  false
    }

    // show dialog there if there is no internet connection
    private fun showNoInternetDialog(){
        AlertDialog.Builder(this@SplashActivity).setTitle("No Internet Connection")
            .setMessage("Please check your internet connection and try again")
            .setPositiveButton(android.R.string.ok){_,_->finish()}
            .setIcon(android.R.drawable.ic_dialog_alert).show()

    }
}