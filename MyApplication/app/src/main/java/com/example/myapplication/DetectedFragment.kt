package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentDetectedBinding
import com.example.myapplication.databinding.FragmentSummaryBinding


class DetectedFragment : Fragment() {
    lateinit var detecAdapter:DetectedAdapter
    lateinit var binding:FragmentDetectedBinding
    var dataList=ArrayList<DetectedInfo>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetectedBinding.inflate(inflater,container,false)

        binding.urlText.text=arguments?.getString("url")
        var bundledata=arguments?.getSerializable("scanList")
        Log.d("bundle",bundledata.toString())

        var parse=bundledata.toString().replace("{","").replace("}","").split(",")
        for(i in parse){
            var a=i.split("=")
            dataList.add(DetectedInfo(a.get(0),a.get(1)))

        }
        init()
        return binding.root
    }

    fun init(){
        var resultdata= arrayListOf<DetectedInfo>()
        detecAdapter=DetectedAdapter(dataList)
        binding.resultRecyclerview.layoutManager=
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        binding.resultRecyclerview.adapter=detecAdapter
   }



}