package net.azarquiel.retrofitcoroutines.api

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.retrofitcoroutines.model.Bar

/**
 * Created by pacopulido on 04/02/2019.
 */

class MainRepository(application: Application) {

    val service = WebAccess.barService

    fun getDataBares(): MutableLiveData<List<Bar>> {
        val result = MutableLiveData<List<Bar>>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.getDataBares().await()
            if (webResponse.isSuccessful) {
                result.value = webResponse.body()!!.bares
            }
        }
        return result
    }

    fun saveBar(nombrebar: String,
                direccion: String,
                municipio: String,
                provincia: String
    ): Bar? {
        var bar:Bar?=null
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.saveBar(nombrebar, direccion, municipio, provincia).await()
            if (webResponse.isSuccessful) {
                bar = webResponse.body()!!.bar
            }
        }
        return bar
    }
}