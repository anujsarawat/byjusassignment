package com.byjus.newsassignment.view

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.byjus.newsassignment.R
import com.byjus.newsassignment.model.datamodels.Article
import kotlinx.android.synthetic.main.activity_headline_details.*
import java.lang.Exception

class NewsDetailsActivity : AppCompatActivity() {

    private var article : Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headline_details)
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        article = intent.getParcelableExtra<Article>("ArticleDetails")

        tvTitle.text = article?.title
        tvAuthorName.text = article?.author ?: "Unknown"
        tvDescription.text = article?.description
        try {
            Glide.with(this)
                .load(article?.urlToImage)
                .into(object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: com.bumptech.glide.request.transition.Transition<in Drawable?>?
                    ) {
                        rootView.background = resource
                    }
                });
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
