package com.example.myapplication.QR

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.Retrofit.APIObject
import com.example.myapplication.Retrofit.VirustotalUrlDto
import com.example.myapplication.databinding.ActivityQractivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QRActivity : AppCompatActivity() {
    val api_key= BuildConfig.MY_API_KEY
    val FragManager=supportFragmentManager.beginTransaction()
    val sumfrag= SummaryFragment()
    val detfrag= DetectedFragment()
    lateinit var binding: ActivityQractivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQractivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        urlscan()
        //TEST


        ////
    }

    fun urlscan(){
        val QRIntent=intent
        val urldata=QRIntent.getStringExtra("url")
        val modeldata=QRIntent.getStringExtra("model")
        Log.d("TEST",api_key)
        lateinit var scanUrl:String
        lateinit var splitUrl:List<String>
        lateinit var scanUrlId:String
        lateinit var res :Map<String, VirustotalUrlDto.Data.Attributes.AnalysisResult>
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    APIObject.scanInterface.postScan(
                        apikey=api_key,
                        url= urldata.toString()
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
                Toast.makeText(applicationContext,"잘못된 QR 코드입니다 . 다시 입력해주세요 .", Toast.LENGTH_LONG).show()
                onBackPressed()



            }

            val response2= withContext(Dispatchers.IO) {
                try {

                    APIObject.resultInterface.getScan(
                        apikey = api_key,
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
                    data2.data.attributes.toString()
                    res=data2.data.attributes.last_analysis_results
                    Log.d("ANALTEST2 NULL 아님",data2.data.attributes.toString())
                    var count=0
                    var scanList=HashMap<String,String>()
                    res.forEach {
                        if (it.value.result=="malicious"||it.value.result=="suspicious"){
                            count++
                            scanList.put(it.key,it.value.result)
                        }

                    }
                    if(scanList.size<2){
                        Log.d("output","검출 X")
                        val bundle=Bundle()
                        bundle.putString("url",urldata)
                        bundle.putString("model",modeldata)
                        sumfrag.arguments=bundle
                        FragManager.add(R.id.Fragment_Container,sumfrag)
                        FragManager.commit()
                    }
                    else {
                        Log.d("output", count.toString() + "개 검출" + scanList.toString())
                        val bundle=Bundle()
                        bundle.putString("url",urldata)
                        bundle.putSerializable("scanList",scanList)
                        detfrag.arguments=bundle
                        FragManager.add(R.id.Fragment_Container,detfrag)
                        FragManager.commit()
                    }


                }
                else{
                    Log.d("ANALTEST2", "Response body is null")
                }
            }
            else{
                Log.d("ANALTEST1", response2.toString())
                Toast.makeText(applicationContext,"API 요청 오류입니다 QR코드를 다시 입력해주세요 .", Toast.LENGTH_LONG).show()
                onBackPressed()

            }
        }


    }

}
