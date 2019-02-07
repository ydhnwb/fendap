package com.starla.fendapbengkulu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import com.starla.fendapbengkulu.adapters.TourGuideAdapter
import com.starla.fendapbengkulu.models.TourGuide
import kotlinx.android.synthetic.main.activity_tour_submenu.*
import kotlinx.android.synthetic.main.content_tour_submenu.*

class TourSubmenuActivity : AppCompatActivity() {

    private var mList = mutableListOf<TourGuide>()
    private lateinit var mAdapter : TourGuideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour_submenu)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { finish() }
        iniate()
        populateData()
    }

    private fun iniate(){
        rv_tour_submenu.layoutManager = LinearLayoutManager(this@TourSubmenuActivity)
    }

    private fun getDataIntent() = intent.getStringExtra("TARGET")

    private fun populateData(){
        val i : String = getDataIntent()
        if(i.equals("PEMANDU")){
            toolbar.title = "Pemandu"
            var f = 0
            while (f < 10){
                mList.add(TourGuide("Pemandu $f", R.drawable.ic_tour_pemandu))
                f++
            }
        }else if(i.equals("HOTEL")){
            var f = 0
            toolbar.title = "Penginapan"
            while (f < 10){
                mList.add(TourGuide("Hotel $f", R.drawable.ic_tour_hotel))
                f++
            }
        }else{
            var f = 0
            toolbar.title = "Transportasi"
            while (f < 10){
                mList.add(TourGuide("Transportasi $f", R.drawable.ic_icon_person))
                f++
            }
        }
        mAdapter = TourGuideAdapter(mList, this@TourSubmenuActivity)
        rv_tour_submenu.adapter = mAdapter
    }
}