package net.azarquiel.recetasclase.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import net.azarquiel.retrofitcoroutines.api.MainRepository
import net.azarquiel.retrofitcoroutines.model.Bar

/**
 * Created by pacopulido on 04/02/2019.
 */

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: MainRepository =
        MainRepository(application)

    fun getDataBares(): MutableLiveData<List<Bar>> {
        return repository.getDataBares()
    }

    fun saveBar(nombrebar: String,
                direccion: String,
                municipio: String,
                provincia: String
    ): Bar? {
        return repository.saveBar(nombrebar, direccion, municipio, provincia)
    }
}
