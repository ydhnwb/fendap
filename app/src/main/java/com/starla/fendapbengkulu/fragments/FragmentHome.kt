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
            i.putExtra("TITLE", "Wisata alam dan buatan")
            i.putExtra("TARGET", "wisata-alam")
            startActivity(i)
        }
        view.card_budaya.setOnClickListener { startActivity(Intent(activity, SubmenuActivity::class.java)) }
        view.card_kuliner.setOnClickListener { startActivity(Intent(activity, SubmenuActivity::class.java)) }
        view.card_kerajinan.setOnClickListener { startActivity(Intent(activity, SubmenuActivity::class.java)) }
        view.card_penginapan.setOnClickListener { startActivity(Intent(activity, SubmenuActivity::class.java)) }

        view.slider_banner.setIndicatorAnimation(IndicatorAnimations.SLIDE)
        view.slider_banner.setAutoScrolling(true)
        view.slider_banner.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION)
        view.slider_banner.setScrollTimeInSec(4)

        val sliderView = DefaultSliderView(activity)
        sliderView.setImageDrawable(R.drawable.banner_1)
        sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        view.slider_banner.addSliderView(sliderView)
    }

}