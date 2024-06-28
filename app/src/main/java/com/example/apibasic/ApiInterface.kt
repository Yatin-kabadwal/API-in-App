package com.example.apibasic

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("product") // Inside this () type the end point of the the site of the api
    fun getProduct (): Call<MyData>
}