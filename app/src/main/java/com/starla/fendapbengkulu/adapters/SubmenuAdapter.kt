package com.starla.fendapbengkulu.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.starla.fendapbengkulu.DetailActivity
import com.starla.fendapbengkulu.R
import com.starla.fendapbengkulu.models.TourismSpot
import com.starla.fendapbengkulu.network.ApiUtil
import com.starla.fendapbengkulu.utilities.Others
import kotlinx.android.synthetic.main.list_item_submenu.view.*

class SubmenuAdapter(val mList : MutableList<TourismSpot>, val context : Context) : RecyclerView.Adapter<SubmenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item_submenu, null, false))

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bindData(context,mList.get(p1))

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindData(context : Context, model : TourismSpot){
            itemView.title.text = model.title
            itemView.description.text = Others.fromHtml(model.description)
            Glide.with(context).load("${ApiUtil.API_URL}public/images/${model.image}").
                    apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.placeholder))
                    .transition(DrawableTransitionOptions().crossFade(200))
                    .into(itemView.photo)
            itemView.setOnClickListener {
                val i = Intent(context, DetailActivity::class.java)
                i.putExtra("TARGET", model.id)
                context.startActivity(i)
            }
        }
    }
}