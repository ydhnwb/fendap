package com.starla.fendapbengkulu.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.starla.fendapbengkulu.R
import com.starla.fendapbengkulu.models.TourGuide
import kotlinx.android.synthetic.main.list_item_tournguide.view.*

class TourGuideAdapter (val mList : List<TourGuide>, val context : Context) : RecyclerView.Adapter<TourGuideAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_item_tournguide, null, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bindData(context, mList.get(p1))

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindData(context: Context, model : TourGuide){
            itemView.submenu_tour_title.text = model.title
            itemView.submenu_tour_image.setImageResource(model.image)
            itemView.setOnClickListener {
                Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}