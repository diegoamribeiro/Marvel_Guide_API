package com.diegoribeiro.marvelguide.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.diegoribeiro.marvelguide.R
import com.diegoribeiro.marvelguide.data.repository.Repository
import com.diegoribeiro.marvelguide.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            getAll()
        }
    }

    private suspend fun getAll(): List<Character>{
        val list = ArrayList<Character>()
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val response = Repository.getAllCharacters()
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    list.addAll(result.data.result)
                }
            }
        }
        Log.d("***Resultado", list.toString())
        return list
    }
}