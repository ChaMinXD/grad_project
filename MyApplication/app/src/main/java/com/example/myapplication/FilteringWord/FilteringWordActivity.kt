package com.example.myapplication.FilteringWord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.DB.FilteringDatabase
import com.example.myapplication.DB.FilteringEntity
import com.example.myapplication.databinding.ActivityFilteringWordBinding

class FilteringWordActivity : AppCompatActivity() {
    lateinit var db: FilteringDatabase
    lateinit var binding: ActivityFilteringWordBinding
    lateinit var wordAdapter: WordAdapter
    lateinit var wordList:ArrayList<FilteringEntity>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFilteringWordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= Room.databaseBuilder(this, FilteringDatabase::class.java,"FilteringDB").allowMainThreadQueries().build()
        init()
        binding.addBtn.setOnClickListener {
            val word=binding.EditText.text.toString()
            db.filteringDao().saveWord(FilteringEntity(word))
            wordList=ArrayList(db.filteringDao().getAll())
            wordAdapter.update(wordList)
        }


    }
    fun init(){

        wordList=ArrayList(db.filteringDao().getAll())
        wordAdapter= WordAdapter(wordList)
        binding.FilteringRecyclerView.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        wordAdapter.clickListener=object: WordAdapter.OnItemClickListener {
            override fun clicked(item: FilteringEntity) {
                //db 삭제
                db.filteringDao().deleteWord(item)
                wordList=ArrayList(db.filteringDao().getAll())
                wordAdapter.update(wordList)
            }
        }
        binding.FilteringRecyclerView.adapter=wordAdapter
    }
}