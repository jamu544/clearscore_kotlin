package android.com.jumpco.io.myapplication.interfaces

import android.com.jumpco.io.myapplication.model.ClearScore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ClearScoreService {

    @POST("/endpoint.json")
    fun getClearScoreInfo(): Call<ClearScore>

}