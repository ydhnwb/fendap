package com.starla.fendapbengkulu.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.DefaultSliderView
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.starla.fendapbengkulu.R
import com.starla.fendapbengkulu.SubmenuActivity
import kotlinx.android.synthetic.main.fragment_home.view.*

class FragmentHome : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.card_wisata.setOnClickListener {
            val i = Intent(activity, SubmenuActivity::class.java)
            i.putExtra("TITLE", "TourismSpot alam dan buatan")
            i.putExtra("TARGET", "wisata-alam-dan-buatan")
            startActivity(i)
        }
        view.card_budaya.setOnClickListener {
            val i = Intent(activity, SubmenuActivity::class.java)
            i.putExtra("TITLE", "TourismSpot budaya")
            i.putExtra("TARGET", "wisata-budaya")
            startActivity(i)
        }
        view.card_kuliner.setOnClickListener {
            val i = Intent(activity, SubmenuActivity::class.java)
            i.putExtra("TITLE", "TourismSpot kuliner")
            i.putExtra("TARGET", "wisata-kuliner")
            startActivity(i)
        }

        view.card_kerajinan.setOnClickListener {
            val i = Intent(activity, SubmenuActivity::class.java)
            i.putExtra("TITLE", "TourismSpot kerajinan")
            i.putExtra("TARGET", "wisata-kerajinan")
            startActivity(i)
        }

        view.card_event.setOnClickListener {
            val i = Intent(activity, SubmenuActivity::class.java)
            i.putExtra("TITLE", "Event dan perayaan")
            i.putExtra("TARGET", "event")
            startActivity(i)
        }

        view.slider_banner.setIndicatorAnimation(IndicatorAnimations.SLIDE)
        view.slider_banner.isAutoScrolling = true
        view.slider_banner.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION)
        view.slider_banner.scrollTimeInSec = 4

        for (i in 0..4) {
            val sliderView = DefaultSliderView(activity)

            when(i){
                0 -> {
                    sliderView.setImageDrawable(R.drawable.banner_1)
                }
                1 -> {
                    sliderView.setImageDrawable(R.drawable.banner_x_4)
                }
                2 -> {
                    sliderView.setImageDrawable(R.drawable.banner_x_3)
                }
                3 -> {
                    sliderView.setImageDrawable(R.drawable.banner_x_2)
                }
                4 -> {
                    sliderView.setImageDrawable(R.drawable.banner_x_1)
                }
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            view.slider_banner.addSliderView(sliderView)
        }
    }

}