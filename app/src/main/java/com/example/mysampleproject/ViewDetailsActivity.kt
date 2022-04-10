package com.example.mysampleproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mysampleproject.viewmodel.PicsumViewModel

class ViewDetailsActivity : AppCompatActivity() {
    lateinit var picsumViewModel: PicsumViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_details)

        val id = intent.getStringExtra("id")
        if (id != null) {
            loadPicsumData(id)
        }
    }

    private fun loadPicsumData(id: String) {
        val authorNameTxt = findViewById<TextView>(R.id.authorNameTxt)
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(R.drawable.img_placeholder)

        picsumViewModel = ViewModelProvider(this).get(PicsumViewModel::class.java)
        picsumViewModel.getPicsumData(id)
        picsumViewModel.picsumData.observe(this) {
            authorNameTxt.text = it.author
            Glide.with(imageView.context)
                .load(it.download_url)
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.img_placeholder)
                .into(imageView)
        }
    }

}