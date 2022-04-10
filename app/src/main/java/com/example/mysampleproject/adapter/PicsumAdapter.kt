package com.example.mysampleproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mysampleproject.R
import com.example.mysampleproject.model.Picsum
import com.google.android.material.textview.MaterialTextView

class PicsumAdapter(
    private val picsumList: List<Picsum>,
    private val onClick: (id: String) -> Unit
) : RecyclerView.Adapter<PicsumAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: MaterialTextView = itemView.findViewById(R.id.authorNameTxt)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val itemLayout: ConstraintLayout = itemView.findViewById(R.id.itemLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.picsum_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = picsumList[position]
        holder.textView1.text = itemsViewModel.author
        Glide.with(holder.imageView.context)
            .load(itemsViewModel.download_url)
            .fitCenter()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.img_placeholder)
            .into(holder.imageView)

        holder.itemLayout.setOnClickListener {
            onClick(itemsViewModel.id)
        }
    }

    override fun getItemCount(): Int = picsumList.size
}