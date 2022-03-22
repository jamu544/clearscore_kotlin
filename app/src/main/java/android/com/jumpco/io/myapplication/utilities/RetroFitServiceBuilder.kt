package android.com.jumpco.io.myapplication.utilities

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitServiceBuilder {

 private val client = OkHttpClient.Builder().build()

                 private val retrofit = Retrofit.Builder()
                             .baseUrl(ENDPOINT_JSON)
                             .addConverterFactory(GsonConverterFactory.create())
                             .client(client)
                             .build()

                 fun<T> buildService(service: Class<T>): T{
                  return retrofit.create(service)
                 }

}