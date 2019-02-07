package com.starla.fendapbengkulu

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.starla.fendapbengkulu.converter.WrappedResponse
import com.starla.fendapbengkulu.models.TourismSpot
import com.starla.fendapbengkulu.network.ApiUtil
import com.starla.fendapbengkulu.utilities.Others
import kotlinx.android.synthetic.main.content_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.design.widget.AppBarLayout


class DetailActivity : AppCompatActivity() {

    private var id : Int = 0
    private var tourismSpotService = ApiUtil.getTourismSpotService()
    private var tourismSpot : TourismSpot = TourismSpot()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar_layout.title = " "
        getIntentData()
        initCollapseToolbar()
    }

    private fun initCollapseToolbar(){
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val isShow = false
            var scrollRange = -1
            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + verticalOffset == 0) {
                val s : String = if (tourismSpot.title == null)  " " else tourismSpot.title
                toolbar_layout.title = s
            } else if (isShow) {
                toolbar_layout.title = " "
            } else if (scrollRange + verticalOffset > 0) {
                toolbar_layout.title = " "
            }
        })
    }

    private fun getIntentData(){
        id = intent.getIntExtra("TARGET", 0)
        fetchData(id)
    }

    private fun fetchData(id : Int){
        if(id == 0){finish()} else {
            detail_loading_image.visibility = View.VISIBLE
            val request : Call<WrappedResponse<TourismSpot>> = tourismSpotService.find(id.toString())
            request.enqueue(object : Callback<WrappedResponse<TourismSpot>>{
                override fun onFailure(call: Call<WrappedResponse<TourismSpot>>, t: Throwable) { Toast.makeText(this@DetailActivity, "Gagal berkoneksi dengan server. Coba lagi nanti", Toast.LENGTH_LONG).show() }

                override fun onResponse(call: Call<WrappedResponse<TourismSpot>>, response: Response<WrappedResponse<TourismSpot>>) {
                    if(response.isSuccessful){
                        val data : WrappedResponse<TourismSpot>? = response.body()
                        if(data != null && data.status == 1){
                            tourismSpot = data.data
                            detail_title.text = tourismSpot.title
                            //detail_description.text = tourismSpot.description
                            detail_description.text = Others.fromHtml(tourismSpot.description)
                            Glide.with(this@DetailActivity).load("${ApiUtil.API_URL}public/images/${tourismSpot.image}")
                                    .apply(RequestOptions().placeholder(R.drawable.placeholder).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                                    .transition(DrawableTransitionOptions().crossFade(200))
                                    .into(detail_image)
                            detail_image.setOnClickListener {
                                val x = Intent(this@DetailActivity, PhotoViewActivity::class.java)
                                x.putExtra("TARGET", "${ApiUtil.API_URL}public/images/${tourismSpot.image}")
                                startActivity(x)
                            }
                        }
                    }else{
                        Toast.makeText(this@DetailActivity, "Terjadi kesalahan. Coba lagi nanti", Toast.LENGTH_LONG).show()
                    }
                    detail_loading_image.visibility = View.INVISIBLE
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}