package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object APIObject {
    private const val BASE_URL="https://www.virustotal.com/api/v3/"

    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val ScanInterface= retrofit.create(UrlScanService::class.java)
    val ResultInterface= retrofit.create(GetAnalysisService::class.java)

}