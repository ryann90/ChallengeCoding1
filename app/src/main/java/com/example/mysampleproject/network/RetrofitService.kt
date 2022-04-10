package com.example.mysampleproject.network

import com.example.mysampleproject.model.Picsum
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("v2/list")
    fun getDataPicsumList(): Call<List<Picsum>>

    @GET("id/{id}/info")
    fun getDataPicsum(@Path("id") id: String):Call<Picsum>
}