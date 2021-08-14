package com.diegoribeiro.marvelguide.data.remote

import android.util.Log
import com.diegoribeiro.marvelguide.utils.Constants.Companion.API_PRIVATE_KEY
import com.diegoribeiro.marvelguide.utils.Constants.Companion.API_PUBLIC_KEY
import com.diegoribeiro.marvelguide.utils.Constants.Companion.BASE_URL
import com.diegoribeiro.marvelguide.utils.md5
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RemoteClient {

companion object {
    fun getService(): MarvelApi {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val ts =
                (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", API_PUBLIC_KEY)
                .addQueryParameter("ts", ts)
                .addQueryParameter("hash", "$ts$API_PRIVATE_KEY$API_PUBLIC_KEY".md5())
                .build()

            chain.proceed(original.newBuilder().url(url).build())
        }

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        return retrofit.create(MarvelApi::class.java)
    }
}
}