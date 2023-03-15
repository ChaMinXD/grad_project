package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
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
            Log.d("SCANTEST","YAHO")


                    APIObject.ScanInterface.PostScan(
                        x_apikey="62efec99495dffbba46eea9cacdf888e41cf9f8d11c778a330795108abd253eb",
                        url="www.naver.com" // 임시로 URL
                    ).enqueue(object: Callback<Data> {
                        override fun onResponse(call: Call<Data>, response: Response<Data>) {
                            if(response.isSuccessful.not()){
                                Log.d("SCANTEST1",response.toString())
                                return
                            }
                            var res=response.body()
                            Log.d("SCANTEST2",response.body().toString())
                        }

                        override fun onFailure(call: Call<Data>, t: Throwable) {
                            Log.d("SCANTEST3",t.toString())
                        }

                    })



        }
    }
}