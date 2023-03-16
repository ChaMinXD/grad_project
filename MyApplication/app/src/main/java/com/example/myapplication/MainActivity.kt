package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.APIObject.ScanInterface
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Base64

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var qrCodeScan = QRCodeScan(this)

        /** Click */
        binding.QR.setOnClickListener {
            qrCodeScan.startQRScan()
        }

        binding.test.setOnClickListener{
            lateinit var scanUrl:String
            lateinit var splitUrl:List<String>
            lateinit var scanUrlId:String
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    try {
                        APIObject.ScanInterface.postScan(
                            apikey="62efec99495dffbba46eea9cacdf888e41cf9f8d11c778a330795108abd253eb",
                            url="www.naver.com"
                        )
                    } catch (e: Exception) {
                        Log.d("SCANTEST3", e.toString())
                        null
                    }
                }

                if (response?.isSuccessful == true) {
                    val data = response.body()

                    if (data != null) {
                        Log.d("SCANTEST2", data.toString())
                        scanUrl=data.data.id
                        splitUrl=scanUrl.split('-')
                        scanUrlId= splitUrl.get(1)

                        Log.d("SCANTEST2",scanUrlId)

                    } else {
                        Log.d("SCANTEST2", "Response body is null")
                    }
                } else {
                    Log.d("SCANTEST1", response.toString())
                }

                val response2= withContext(Dispatchers.IO) {
                    try {
                        APIObject.ResultInterface.GetScan(
                            apikey = "62efec99495dffbba46eea9cacdf888e41cf9f8d11c778a330795108abd253eb",
                            id = scanUrlId
                        )
                    } catch (e: Exception) {
                        Log.d("ANALTEST3", e.toString())
                        null

                    }
                }
                if(response2?.isSuccessful==true){
                    val data2=response2.body()
                    if(data2!=null){
                        Log.d("ANALTEST2",data2.toString())

                    }
                    else{
                        Log.d("ANALTEST2", "Response body is null")
                    }
                }
                else{
                    Log.d("ANALTEST1", response2.toString())
                }
            }



        }
    }
}