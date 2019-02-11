package net.azarquiel.retrofitcoroutines.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pacopulido on 04/02/2019.
 */

object WebAccess {

    val barService : BarService by lazy {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://www.ies-azarquiel.es/paco/apibar/")
            .build()

        return@lazy retrofit.create(BarService::class.java)
    }
}