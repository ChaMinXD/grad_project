package com.example.myapplication.QR

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.DetectedRowBinding

class DetectedAdapter(var items:ArrayList<DetectedInfo>):RecyclerView.Adapter<DetectedAdapter.ViewHolder>(){
    inner class ViewHolder(val binding:DetectedRowBinding):RecyclerView.ViewHolder(binding.root){
        init{

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:DetectedRowBinding=
            DetectedRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.rowVaccine.text=items[position].vaccine
        holder.binding.rowResult.text="해당 백신에서 바이러스가 검출되었습니다 . "

    }

    override fun getItemCount(): Int {
        return items.size
    }
}