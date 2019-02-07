package com.starla.fendapbengkulu.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.starla.fendapbengkulu.R
import com.starla.fendapbengkulu.models.ChatModel
import kotlinx.android.synthetic.main.list_item_chat_receive.view.*
import kotlinx.android.synthetic.main.list_item_chat_sent.view.*

class RuangKitoAdapter (val mList : MutableList<ChatModel>, val context : Context) : RecyclerView.Adapter<RuangKitoAdapter.ViewHolder>() {
    val isMe = 1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        if(isMe == p1){
            val v = LayoutInflater.from(p0.context).inflate(R.layout.list_item_chat_sent, p0 , false)
            return ViewHolder(v)
        }else{
            val v = LayoutInflater.from(p0.context).inflate(R.layout.list_item_chat_receive, p0 , false)
            return ViewHolder(v)
        }
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val message = mList[p1]
        if(message.isMe == isMe){
            p0.bindDataSent(message, context)
        }else{
            p0.bindDataReceive(message, context)
        }
    }

    override fun getItemViewType(position: Int) = mList.get(position).isMe


    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindDataSent(model : ChatModel, context: Context){
            itemView.message_message_me.text = model.message
            itemView.setOnLongClickListener {
                val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val myClipData = ClipData.newPlainText("captions", model.message)
                clipboardManager.primaryClip = myClipData
                Toast.makeText(context, "Teks berhasil disalin", Toast.LENGTH_SHORT).show()
                return@setOnLongClickListener false
            }
        }

        fun bindDataReceive(model : ChatModel, context: Context){
            itemView.message_message.text = model.message
            itemView.message_sender.text = model.user
            itemView.setOnLongClickListener {
                val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val myClipData = ClipData.newPlainText("captions", model.message)
                clipboardManager.primaryClip = myClipData
                Toast.makeText(context, "Teks berhasil disalin", Toast.LENGTH_SHORT).show()
                return@setOnLongClickListener false
            }
        }
    }
}