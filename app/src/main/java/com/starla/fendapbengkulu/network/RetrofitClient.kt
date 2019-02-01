package com.starla.fendapbengkulu.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        var retrofit : Retrofit? = null
        var h = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();
        fun getClient(url : String) : Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(url).client(h).addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }
    }
}