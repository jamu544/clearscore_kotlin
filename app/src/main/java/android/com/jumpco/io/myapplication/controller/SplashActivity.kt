package android.com.jumpco.io.myapplication.controller

import android.com.jumpco.io.myapplication.R
import android.com.jumpco.io.myapplication.interfaces.ClearScoreService
import android.com.jumpco.io.myapplication.model.ClearScore
import android.com.jumpco.io.myapplication.model.ClearScoreDetails
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


   // class ClearScoreDetails(var score:Int, var maxScore:Int, var personaType: String?)
     var clearScoreDetails = ClearScoreDetails(0,0,"",
       "","","")



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_DETAILS, clearScoreDetails)
    }

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

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null){
            clearScoreDetails = savedInstanceState.getParcelable(EXTRA_DETAILS)!!

        }
    }
    //display splashscreen and load the next activity
    private fun loadSplashScreen(){
        Handler1().postDelayed({

            val donutActivity = Intent(this, DonutActivity::class.java)
            donutActivity.putExtra(EXTRA_DETAILS,clearScoreDetails)
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
            override fun onResponse(call: Call<ClearScore>?,response: Response<ClearScore>?) {
                if(response?.isSuccessful == true) {

                    clearScoreDetails.score = response?.body()?.creditReportInfo?.score!!
                    clearScoreDetails.maxScore = response?.body()?.creditReportInfo?.maxScoreValue!!
                    clearScoreDetails.personaType = response?.body()?.personaType
                    clearScoreDetails.clientRef = response?.body()?.creditReportInfo?.clientRef
                    clearScoreDetails.status = response?.body()?.creditReportInfo?.status


                    Log.d("Response successful", response.body().toString())
                }
                else {
                    if (response != null) {
                        Log.d("Respone", response.raw().toString())
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
      fun isNetWorkConnected(): Boolean {
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
        AlertDialog.Builder(this@SplashActivity,
            R.style.Theme_AppCompat_Dialog).setTitle("No Internet Connection")
            .setCancelable(false)
            .setMessage("Please check your internet connection and try again")
            .setPositiveButton(android.R.string.ok){_,_->finish()}
            .setIcon(android.R.drawable.ic_dialog_alert).show()
    }
}