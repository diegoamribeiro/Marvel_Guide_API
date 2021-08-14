package com.diegoribeiro.marvelguide.data.remote

import com.diegoribeiro.marvelguide.model.Data
import com.diegoribeiro.marvelguide.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    suspend fun getAllCharacters(@Query("offset") offset: Int? = 0): Response<Data>
}