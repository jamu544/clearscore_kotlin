package android.com.jumpco.io.myapplication.interfaces

import android.com.jumpco.io.myapplication.pojo.ClearScoreModel
import retrofit2.Call
import retrofit2.http.GET

interface ClearScoreService {

    @GET("/endpoint.json")
    fun getClearScoreInfo(): Call<ClearScoreModel>

}