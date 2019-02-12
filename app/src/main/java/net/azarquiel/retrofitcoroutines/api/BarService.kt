package net.azarquiel.retrofitcoroutines.api

import kotlinx.coroutines.Deferred
import net.azarquiel.retrofitcoroutines.model.Respuesta
import net.azarquiel.retrofitcoroutines.model.Usuario
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by pacopulido on 04/02/2019.
 */


interface BarService {

    @GET("bar")
    fun getDataBares(): Deferred<Response<Respuesta>>

    // idbar en la url
    @GET("bar/{idbar}")
    fun getDataBar(@Path("idbar") idbar: Int): Deferred<Response<Respuesta>>

    @GET("provincia")
    fun getDataProvincias(): Deferred<Response<Respuesta>>

    @GET("bar/provincia/{provincia}")
    fun getDataBaresProvincia(@Path("provincia") provincia: String): Deferred<Response<Respuesta>>

    // nick y pass como variables en la url?nick=paco&pass=paco
    @GET("usuario")
    fun getDataUsuarioPorNickPass(
            @Query("nick") nick: String,
            @Query("pass") pass: String): Deferred<Response<Respuesta>>

    @GET("usuario/{idusuario}")
    fun getDataUsuarioPorId(@Path("idusuario") idusuario: String): Deferred<Response<Respuesta>>

    @GET("bar/{idbar}/puntos")
    fun getDataAvgPuntosBar(@Path("idbar") idbar: Int): Deferred<Response<Respuesta>>

    // post con variables sueltas
    @FormUrlEncoded
    @POST("bar")
    fun saveBar(@Field("nombrebar") nombrebar: String,
                @Field("direccion") direccion: String,
                @Field("municipio") municipio: String,
                @Field("provincia") provincia: String
                            ): Deferred<Response<Respuesta>>

    // post con objeto en json
    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Respuesta>>

    // post con variables sueltas
    @FormUrlEncoded
    @POST("usuario")
    fun saveUsuario(@Field("nick") nick:String,
                    @Field("pass") pass:String): Deferred<Response<Respuesta>>

    @FormUrlEncoded
    @POST("bar/{idbar}/puntos")
    fun savePuntos(@Path("idbar") idbar: Int,
                   @Field("idusuario") idusuario: Int,
                   @Field("puntos") puntos: Int): Deferred<Response<Respuesta>>
}