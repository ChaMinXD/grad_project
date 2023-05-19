package com.example.myapplication.QR

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.myapplication.DB.FilteringDatabase
import com.example.myapplication.Retrofit.APIObject
import com.example.myapplication.Retrofit.SummaryRequest
import com.example.myapplication.databinding.FragmentSummaryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


class SummaryFragment : Fragment() {
    lateinit var url: String
    lateinit var db: FilteringDatabase
    lateinit var model:String
    lateinit var binding: FragmentSummaryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSummaryBinding.inflate(inflater,container,false)
        url= arguments?.getString("url").toString()
        model=arguments?.getString("model").toString()

        val filteringWord=ArrayList<String>() // 필터링단어
        binding.urlText.text=url
        db= Room.databaseBuilder(requireContext(), FilteringDatabase::class.java,"FilteringDB").allowMainThreadQueries().build()

        init()
        ServerAPI()
        return binding.root

    }


    fun init() {
        binding.urlLink.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(myIntent)
        }
        binding.sumText.movementMethod=ScrollingMovementMethod.getInstance()
        val webView = binding.webView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

                // 웹 페이지가 로드되면 썸네일 이미지 생성
                val bitmap = Bitmap.createBitmap(
                    webView.width, webView.height, Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                webView.draw(canvas)

                val file = File(binding.root.context.cacheDir, "thumbnail.png")
                val fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                val resizeBit=Bitmap.createScaledBitmap(bitmap, 309, 300, true)
                binding.thumImg.setImageBitmap(resizeBit)
                fos.close()
            }
        }
        webView.loadUrl(url)


    }
    fun ServerAPI(){
        var APITEST = db.filteringDao().getAll().map {it.filteringWord}
        Log.d("APITEST",APITEST.toString())
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    APIObject.summaryInterface.getSummary(
                        SummaryRequest(url, APITEST )
                        ,model=model
                    )
                } catch (e: Exception) {
                    Log.d("SERVERTEST1", e.toString())
                    null
                }
            }
            if (response?.isSuccessful == true) {
                val data = response.body()

                if (data != null) {
                    Log.d("SERVERTEST2", data.toString())
                    if(data.passed==true){
                        binding.sumText.text=data.content
                    }
                    else{
                        binding.sumText.text="사용자가 설정한 단어 필터링으로 인해 내용이 필터링 되었습니다 . 해당 링크로 접속을 원하시면 위에 링크 아이콘을 눌러주세요 ."
                    }

                } else {
                    Log.d("SERVERTEST3", "Response body is null")
                }
            } else {
                Log.d("SERVERTEST4", response.toString())
            }

        }
    }

}