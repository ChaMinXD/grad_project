package com.example.myapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.myapplication.APIObject.ScanInterface
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.*
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Base64
import java.util.Properties

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val api_key = BuildConfig.MY_API_KEY
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /** Click */
        binding.QRICON.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) // 여러가지 바코드중에 특정 바코드 설정 가능
            integrator.setPrompt("QR 코드를 스캔하여 주세요:)") // 스캔할 때 하단의 문구
            integrator.setCameraId(0) // 0은 후면 카메라, 1은 전면 카메라
            integrator.setBeepEnabled(true) // 바코드를 인식했을 때 삑 소리유무
            integrator.setBarcodeImageEnabled(false) // 스캔 했을 때 스캔한 이미지 사용여부
            integrator.initiateScan() // 스캔

        }


        binding.FilterIcon.setOnClickListener {


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // QR 코드를 찍은 결과를 변수에 담는다.
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Log.d("TTT", "QR 코드 체크")

        //결과가 있으면
        if (result != null) {
            // 컨텐츠가 없으면
            if (result.contents == null) {
                //토스트를 띄운다.
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            }
            // 컨텐츠가 있으면
            else {
                //토스트를 띄운다.
                Toast.makeText(this, "scanned" + result.contents, Toast.LENGTH_LONG).show()
                Log.d("TTT", "QR 코드 URL:${result.contents}")
                val intent = Intent(this, QRActivity::class.java)
                intent.putExtra("url", result.contents.toString())
                startActivity(intent)


            }
            // 결과가 없으면
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}