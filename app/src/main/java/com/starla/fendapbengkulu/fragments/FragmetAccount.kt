package com.starla.fendapbengkulu.fragments

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.starla.fendapbengkulu.LoginActivity
import com.starla.fendapbengkulu.R
import kotlinx.android.synthetic.main.fragment_account.view.*

class FragmetAccount : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.go_to_login.setOnClickListener { startActivity(Intent(activity, LoginActivity::class.java)) }
    }

    override fun onResume() {
        super.onResume()
        isLoggedIn()
    }

    private fun isLoggedIn(){
        val settings = activity!!.getSharedPreferences("TOKEN", MODE_PRIVATE)
        val token = settings.getString("TOKEN", "UNDEFINED")
        if(token == null || token.equals("UNDEFINED")){
            view?.go_to_login?.isClickable = true
            view?.need_login?.text = "Anda belum masuk"
            view?.need_login_desc?.text = "Anda perlu masuk untuk dapat mengirim komentar dan rating"
        }else{
            view?.go_to_login?.isClickable = false
            view?.need_login?.text = "Prieyudha Akadita S"
            view?.need_login_desc?.text = "yudhanewbie@gmail.com"
        }
    }
}