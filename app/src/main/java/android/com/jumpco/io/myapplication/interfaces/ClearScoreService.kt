package android.com.jumpco.io.myapplication.interfaces

import android.com.jumpco.io.myapplication.model.ClearScore
import android.com.jumpco.io.myapplication.utilities.ENDPOINT_JSON
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

interface ClearScoreService {

    @GET("/endpoint.json")
    fun getClearScoreInfo(): Call<ClearScore>

    companion object {


        fun create(): ClearScoreService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ENDPOINT_JSON)
                .build()
            return retrofit.create(ClearScoreService::class.java)

        }

    }
}