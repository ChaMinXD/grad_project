package com.example.myapplication.FilteringWord

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DB.FilteringEntity
import com.example.myapplication.databinding.FilteringRowBinding

class WordAdapter(var items:ArrayList<FilteringEntity>):RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:FilteringRowBinding):RecyclerView.ViewHolder(binding.root){
        init{
            binding.Xbtn.setOnClickListener {
                clickListener?.clicked(items[adapterPosition])
            }
        }
    }
    var clickListener: OnItemClickListener?=null
    interface OnItemClickListener{
        fun clicked(item: FilteringEntity)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:FilteringRowBinding=
            FilteringRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.filteringWordText.text=items[position].filteringWord

    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun update(newlist:ArrayList<FilteringEntity>){
        items.clear()
        items.addAll(newlist)
        this.notifyDataSetChanged()
    }


}