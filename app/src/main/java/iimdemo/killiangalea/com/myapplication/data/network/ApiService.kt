package iimdemo.killiangalea.com.myapplication.data.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("login")
    fun login(): Call<Void>
}