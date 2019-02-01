package com.starla.fendapbengkulu.services

import com.starla.fendapbengkulu.converter.WrappedListResponse
import com.starla.fendapbengkulu.converter.WrappedResponse
import com.starla.fendapbengkulu.models.Wisata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WisataService {
    @GET("api/tour")
    fun all() : Call<WrappedListResponse<Wisata>>

    @GET("api/tour/{id}")
    fun find(@Path("id") id: String): Call<WrappedResponse<Wisata>>
}