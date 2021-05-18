package iimdemo.killiangalea.com.myapplication.data.network

import iimdemo.killiangalea.com.myapplication.data.model.NasaFeed
import retrofit2.Call
import retrofit2.http.GET

interface NasaService {

    @GET("apod?count=10&api_key=VdavqHM43OU7Gqwde2KEX1NikCwc9q9hRCuguZAq")
    fun getNasaFeed(): Call<List<NasaFeed>>
}