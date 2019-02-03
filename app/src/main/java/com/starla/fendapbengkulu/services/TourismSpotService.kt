package com.starla.fendapbengkulu.services

import com.starla.fendapbengkulu.converter.WrappedListResponse
import com.starla.fendapbengkulu.converter.WrappedResponse
import com.starla.fendapbengkulu.models.TourismSpot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TourismSpotService {
    @GET("api/tour")
    fun all() : Call<WrappedListResponse<TourismSpot>>

    @GET("api/tour/{id}")
    fun find(@Path("id") id: String): Call<WrappedResponse<TourismSpot>>

    @GET("api/tour/category/{c}")
    fun category(@Path("c") c: String): Call<WrappedListResponse<TourismSpot>>
}