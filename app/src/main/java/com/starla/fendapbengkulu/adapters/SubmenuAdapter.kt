package com.starla.fendapbengkulu.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.starla.fendapbengkulu.R
import com.starla.fendapbengkulu.models.Wisata
import kotlinx.android.synthetic.main.list_item_submenu.view.*

class SubmenuAdapter(val mList : MutableList<Wisata>, val context : Context) : RecyclerView.Adapter<SubmenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item_submenu, null, false))

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bindData(context,mList.get(p1))

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindData(context : Context, model : Wisata){
            itemView.title.text = model.title
            itemView.description.text = model.description
            Glide.with(context).load("image/${model.image}").into(itemView.photo)
        }
    }
}