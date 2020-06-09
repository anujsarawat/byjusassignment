package com.byjus.newsassignment.view.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.byjus.newsassignment.R
import com.byjus.newsassignment.model.datamodels.Article
import kotlinx.android.synthetic.main.item_headlines.view.*
import java.lang.Exception

internal class NewsListAdapter(private val context: Context, private val articles: ArrayList<Article>,
                               private val newsItemClickListener: NewsItemClickListener
): RecyclerView.Adapter<NewsListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_headlines, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {
            Glide.with(context)
                .load(articles[position].urlToImage)
                .into(object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: com.bumptech.glide.request.transition.Transition<in Drawable?>?
                    ) {
                        holder.itemView.progress_bar.visibility = View.GONE
                        holder.itemView.layoutHeadline.visibility = View.VISIBLE
                        holder.itemView.rootView.background = resource
                        holder.itemView.tvNewsTitle.text = articles[position].title
                        if (articles[position].author != null && articles[position].author?.isNotEmpty()!!) {
                            holder.itemView.tvAuthorName.text = articles[position].author
                        }
                    }
                });
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.rootView.setOnClickListener{
                newsItemClickListener.onNewsItemClicked(bindingAdapterPosition)
            }
        }
    }

    interface NewsItemClickListener {
        fun onNewsItemClicked(position: Int)
    }

}