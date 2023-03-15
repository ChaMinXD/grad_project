package com.example.myapplication

import okhttp3.Response
import retrofit2.http.*
import retrofit2.Call


interface UrlScanService {
    @FormUrlEncoded
    @POST("urls")
    fun PostScan(
        @Header("x-apikey") x_apikey:String,
        @Field ("url") url:String) : Call<Data>
}
interface GetAnalysisService{
    @GET("/urls/{id}")
    fun GetScan(
        @Header("x-apikey") key:String,
        @Path("id") id:String) :Call<last_analysis_results>
}