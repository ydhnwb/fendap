package com.starla.fendapbengkulu.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.starla.fendapbengkulu.R
import com.starla.fendapbengkulu.models.Person
import kotlinx.android.synthetic.main.list_item_about.view.*

class AboutAdapter(val listOfPerson : MutableList<Person>, val context : Context) : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_item_about, null, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listOfPerson.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bindData(listOfPerson.get(p1), context)

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindData(model : Person, context: Context){
            itemView.about_name.text = model.name
            itemView.about_email.text = model.email
            itemView.about_photo.setImageResource(model.photo)
            itemView.setOnClickListener {
                Toast.makeText(context, model.name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}