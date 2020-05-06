package com.example.android.todonotesapp.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.todonotesapp.R
import com.example.android.todonotesapp.model.Data

class BlogsAdapter (val list:List<Data>):RecyclerView.Adapter<BlogsAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val imageView:ImageView = itemView.findViewById(R.id.imageView)
        val textViewTitle :TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription:TextView = itemView.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blog = list[position]
        Glide.with(holder.itemView).load(blog.img_url).into(holder.imageView)
        holder.textViewTitle.text= blog.title
        holder.textViewDescription.text= blog.description

    }
}