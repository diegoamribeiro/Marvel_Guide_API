package com.diegoribeiro.marvelguide.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.diegoribeiro.marvelguide.R
import com.diegoribeiro.marvelguide.data.remote.RemoteClient
import com.diegoribeiro.marvelguide.model.Data
import com.diegoribeiro.marvelguide.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gettAll()
    }

    private fun gettAll(): List<Result>{
        val list = arrayListOf<Result>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = RemoteClient.getService().getAllCharacters(10)
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