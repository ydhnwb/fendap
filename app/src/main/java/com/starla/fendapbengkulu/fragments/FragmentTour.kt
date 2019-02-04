package com.starla.fendapbengkulu.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.starla.fendapbengkulu.R
import com.starla.fendapbengkulu.TourSubmenuActivity
import kotlinx.android.synthetic.main.fragment_tour.view.*

class FragmentTour : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tour, null , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.tour_hotel.setOnClickListener {
            val i = Intent(activity, TourSubmenuActivity::class.java)
            i.putExtra("TARGET", "HOTEL")
            startActivity(i)
        }

        view.tour_pemandu.setOnClickListener {
            val i = Intent(activity, TourSubmenuActivity::class.java)
            i.putExtra("TARGET", "PEMANDU")
            startActivity(i)
        }

        view.tour_transportasi.setOnClickListener {
            val i = Intent(activity, TourSubmenuActivity::class.java)
            i.putExtra("TARGET", "TRANSPORTASI")
            startActivity(i)
        }
    }
}