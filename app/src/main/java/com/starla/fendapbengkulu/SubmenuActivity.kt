package com.starla.fendapbengkulu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.starla.fendapbengkulu.adapters.SubmenuAdapter
import com.starla.fendapbengkulu.converter.WrappedListResponse
import com.starla.fendapbengkulu.models.Wisata
import com.starla.fendapbengkulu.network.ApiUtil
import kotlinx.android.synthetic.main.activity_submenu.*
import kotlinx.android.synthetic.main.content_submenu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmenuActivity : AppCompatActivity() {

    private var title = ""
    private var target = ""
    private lateinit var submenuAdapter: SubmenuAdapter
    private val wisataService = ApiUtil.getWisataService();

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
        toolbar.title = title
        rv_submenu.layoutManager = LinearLayoutManager(this@SubmenuActivity)
    }

    private fun getIntentData(){
        title = intent.getStringExtra("TITLE")
        target = intent.getStringExtra("TARGET")
    }

    private fun loadData(){
        if(target.isNotEmpty()){
            val request : Call<WrappedListResponse<Wisata>> = wisataService.all()
            request.enqueue(object : Callback<WrappedListResponse<Wisata>>{
                override fun onFailure(call: Call<WrappedListResponse<Wisata>>, t: Throwable) {
                    Toast.makeText(this@SubmenuActivity, "Tidak dapat mengambil data dari server. Coba lagi nanti", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<WrappedListResponse<Wisata>>, response: Response<WrappedListResponse<Wisata>>) {
                    if(response.isSuccessful){
                        val data : WrappedListResponse<Wisata>? = response.body()
                        if(data != null && data.status == 1){
                            val mList : MutableList<Wisata> = data.data
                            submenuAdapter = SubmenuAdapter(mList, this@SubmenuActivity)
                            rv_submenu.adapter = submenuAdapter
                        }
                    }else{
                        Toast.makeText(this@SubmenuActivity, "Terjadi kesalahan, coba lagi nanti", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }else{
            Toast.makeText(this@SubmenuActivity, "Terjadi kesalahan. Coba lagi nanti", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

}
