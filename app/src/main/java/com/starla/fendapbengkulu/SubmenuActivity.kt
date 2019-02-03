package com.starla.fendapbengkulu

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.starla.fendapbengkulu.adapters.SubmenuAdapter
import com.starla.fendapbengkulu.converter.WrappedListResponse
import com.starla.fendapbengkulu.models.TourismSpot
import com.starla.fendapbengkulu.network.ApiUtil
import com.starla.fendapbengkulu.utilities.Others
import kotlinx.android.synthetic.main.activity_submenu.*
import kotlinx.android.synthetic.main.content_submenu.*
import kotlinx.android.synthetic.main.etc_empty_view.*
import kotlinx.android.synthetic.main.etc_failure_request.*
import kotlinx.android.synthetic.main.etc_submenu_shimmer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmenuActivity : AppCompatActivity() {

    private var title = ""
    private var target = ""
    private val wisataService = ApiUtil.getWisataService()
    private lateinit var submenuAdapter: SubmenuAdapter
    private var mList : MutableList<TourismSpot> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submenu)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { finish() }
        initComp()
    }


    private fun initComp(){
        getIntentData()
        toolbar.setTitle(title)
        rv_submenu.layoutManager = LinearLayoutManager(this@SubmenuActivity)
    }

    private fun getIntentData(){
        title = intent.getStringExtra("TITLE")
        target = intent.getStringExtra("TARGET")
    }

    private fun loadData(){
        if(target.isNotEmpty() && mList.isEmpty()){
            etc_empty.visibility = View.INVISIBLE
            etc_failure.visibility = View.INVISIBLE
            ui_submenu_shimmer.visibility = View.VISIBLE
            ui_submenu_shimmer.startShimmerAnimation()
            val request : Call<WrappedListResponse<TourismSpot>> = wisataService.category(target)
            request.enqueue(object : Callback<WrappedListResponse<TourismSpot>>{
                override fun onFailure(call: Call<WrappedListResponse<TourismSpot>>, t: Throwable) {
                    System.err.println("starla : "+t.message)
                    ui_submenu_shimmer.stopShimmerAnimation()
                    ui_submenu_shimmer.visibility = View.INVISIBLE
                    etc_failure.visibility = View.VISIBLE
                    Toast.makeText(this@SubmenuActivity, "Tidak dapat mengambil data dari server. Coba lagi nanti", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<WrappedListResponse<TourismSpot>>, response: Response<WrappedListResponse<TourismSpot>>) {
                    if(response.isSuccessful){
                        val data : WrappedListResponse<TourismSpot>? = response.body()
                        if(data != null && data.status == 1){
                            //val mList : MutableList<TourismSpot> = data.data
                            mList.clear()
                            mList = data.data
                            submenuAdapter = SubmenuAdapter(mList, this@SubmenuActivity)
                            rv_submenu.adapter = submenuAdapter
                            if(mList.isEmpty()){
                                ui_submenu_shimmer.stopShimmerAnimation()
                                ui_submenu_shimmer.visibility = View.INVISIBLE
                                etc_empty.visibility = View.VISIBLE
                            }
                        }
                    }else{
                        ui_submenu_shimmer.stopShimmerAnimation()
                        ui_submenu_shimmer.visibility = View.INVISIBLE
                        etc_failure.visibility = View.VISIBLE
                        Toast.makeText(this@SubmenuActivity, "Terjadi kesalahan, coba lagi nanti", Toast.LENGTH_LONG).show()
                    }
                    ui_submenu_shimmer.stopShimmerAnimation()
                    ui_submenu_shimmer.visibility = View.INVISIBLE
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if(mList.isEmpty()){
            loadData()
        }
    }
}
