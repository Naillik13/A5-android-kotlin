package iimdemo.killiangalea.com.myapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iimdemo.killiangalea.com.myapplication.R
import iimdemo.killiangalea.com.myapplication.data.model.NasaFeed
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedAdapter(private val feedList: ArrayList<NasaFeed>) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = feedList.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) = holder.bind(feedList[position])

    class FeedViewHolder(private val feedView: View) : RecyclerView.ViewHolder(feedView) {

        fun bind(feed: NasaFeed) {
            feedView.feedTitle.text = feed.title
            feedView.feedDate.text = feed.date
            feedView.feedDescription.text = feed.explanation
            Picasso.get().load(feed.url).into(feedView.feedImage)
        }

    }
}