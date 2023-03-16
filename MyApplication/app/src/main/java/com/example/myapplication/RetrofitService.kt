package com.example.myapplication

import retrofit2.http.*
import retrofit2.Call
import retrofit2.Response


interface UrlScanService {
    @FormUrlEncoded
    @POST("urls")
    suspend fun postScan(
        @Header("x-apikey") apikey:String,
        @Field ("url") url:String) : Response<ScanData>
}
interface GetAnalysisService{
    @GET("urls/{id}")
    suspend fun GetScan(
        @Header("x-apikey") apikey:String,
        @Path("id") id:String) :Response<AnalData>
}