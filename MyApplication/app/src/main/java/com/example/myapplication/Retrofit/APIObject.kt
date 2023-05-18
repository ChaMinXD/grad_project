package com.example.myapplication.Retrofit

import com.example.myapplication.GetAnalysisService
import com.example.myapplication.GetSummaryService
import com.example.myapplication.UrlScanService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIObject {
    private const val VIRUSTOTAL_URL="https://www.virustotal.com/api/v3/"
    private const val BASE_URL="http://203.252.166.225:8080"
    private val OkHttpClient =OkHttpClient().newBuilder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build();
    private val vt_retrofit=Retrofit.Builder()
        .baseUrl(VIRUSTOTAL_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val base_retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient)
        .build()



    val scanInterface= vt_retrofit.create(UrlScanService::class.java)
    val resultInterface= vt_retrofit.create(GetAnalysisService::class.java)
    val summaryInterface= base_retrofit.create(GetSummaryService::class.java)

}