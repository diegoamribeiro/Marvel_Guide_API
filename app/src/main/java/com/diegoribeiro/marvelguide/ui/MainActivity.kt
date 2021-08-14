package com.diegoribeiro.marvelguide.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.diegoribeiro.marvelguide.R
import com.diegoribeiro.marvelguide.data.repository.Repository
import com.diegoribeiro.marvelguide.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gettAll()
    }

    private fun gettAll(): List<Character>{
        val list = ArrayList<Character>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = Repository.getAllCharacters()
            if (response.isSuccessful){
                response.body()?.let {result->
                    list.addAll(result.results)
                }
            }
        }
        Log.d("***Resultado", list.toString())
        return list
    }
}