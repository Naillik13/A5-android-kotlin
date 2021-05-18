package iimdemo.killiangalea.com.myapplication.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import iimdemo.killiangalea.com.myapplication.presentation.FeedAdapter
import iimdemo.killiangalea.com.myapplication.R
import iimdemo.killiangalea.com.myapplication.data.model.NasaFeed
import iimdemo.killiangalea.com.myapplication.data.network.NasaClient
import kotlinx.android.synthetic.main.activity_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedActivity : AppCompatActivity() {

    private var feedList: ArrayList<NasaFeed> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FeedAdapter(feedList)

        fetchNasaFeed()
    }

    private fun fetchNasaFeed() {

        //Reset feed list
        feedList.clear()
        recyclerView.adapter?.notifyDataSetChanged()

        val call = NasaClient.nasaService.getNasaFeed()

        call.enqueue(object : Callback<List<NasaFeed>> {
            override fun onResponse(call: Call<List<NasaFeed>>, response: Response<List<NasaFeed>>) {
                if (response.code() == 200) {
                    feedList.addAll(response.body()!!)

                    //Stop refreshing and update adapter
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<NasaFeed>>, t: Throwable) {
                Log.d("FeedActivity","Error when fetching Nasa Feed")
            }
        })
    }
}