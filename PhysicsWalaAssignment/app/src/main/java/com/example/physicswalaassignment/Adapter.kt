package com.example.physicswalaassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(private val datalist: MutableList<Profile>) : RecyclerView.Adapter<MyViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.profile_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = datalist[position]
        holder.name.text = currentItem.name
        holder.subject.text = currentItem.subject
        holder.college.text = currentItem.qualification
        Glide.with(holder.itemView.context).load(currentItem.img).into(holder.img)
    }

}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var name: TextView = itemView.findViewById(R.id.name)
    var subject: TextView = itemView.findViewById(R.id.LV_subject)
    var college: TextView = itemView.findViewById(R.id.LV_qualification)
    var img: ImageView = itemView.findViewById(R.id.img_profile)

}