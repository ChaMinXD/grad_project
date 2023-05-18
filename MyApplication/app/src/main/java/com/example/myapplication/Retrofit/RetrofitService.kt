package com.example.myapplication

import com.example.myapplication.Retrofit.ScanData
import com.example.myapplication.Retrofit.SummaryData
import com.example.myapplication.Retrofit.SummaryRequest
import com.example.myapplication.Retrofit.VirustotalUrlDto
import retrofit2.http.*
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
    suspend fun getScan(
        @Header("x-apikey") apikey:String,
        @Path("id") id:String) : Response<VirustotalUrlDto>
}

interface GetSummaryService{
    @POST ("summary")
    suspend fun getSummary(
        @Body summary: SummaryRequest,
        @Query ("model") model:String
    ):Response<SummaryData>
}
