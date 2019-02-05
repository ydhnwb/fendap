package com.starla.fendapbengkulu.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.starla.fendapbengkulu.R
import com.starla.fendapbengkulu.adapters.RuangKitoAdapter
import com.starla.fendapbengkulu.models.ChatModel
import kotlinx.android.synthetic.main.fragment_ruangkito.view.*

class FragmentRuang : Fragment() {

    private var mList = mutableListOf<ChatModel>()
    private lateinit var mAdapter : RuangKitoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ruangkito, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rv_ruang_kito.layoutManager = LinearLayoutManager(activity)
        mList.add(ChatModel("Yogi", "Lapor min, pantai panjang sore tadi banyak tumpkan sampah! Kira-kira gimana solusinya?, kalau nunggu pemerintah kelamaan min", 0))
        mList.add(ChatModel("Algi", "iyo benar min ambo pulo nengok tadi,, solusi yang tepatnyo lemak kito aksi bersih pantai bae minggu besok, kito ajak galo kawan-kawan tu, cak mano? ", 0))
        mList.add(ChatModel("Ryan", "Way CAMKOHA nian itu,, ambo setuju kalau soluisnyo cak itu, mari  kito buat pariwisata kito tetap terjaga dan terawat kearifan lokalnyo!! ", 0))
        mAdapter = RuangKitoAdapter(mList, activity!!)
        view.rv_ruang_kito.adapter = mAdapter
        view.rv_ruang_kito.scrollToPosition(mList.size-1)
    }
}