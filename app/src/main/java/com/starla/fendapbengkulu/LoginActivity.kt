package com.starla.fendapbengkulu

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast
import com.starla.fendapbengkulu.converter.WrappedResponse
import com.starla.fendapbengkulu.models.User
import com.starla.fendapbengkulu.network.ApiUtil
import com.starla.fendapbengkulu.utilities.Others
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var mProgressDialog : ProgressDialog
    private var userService = ApiUtil.getUserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { finish() }
        initComponents()
        doLogin()
    }

    private fun initComponents(){
        tv_login_desc.text = Others.fromHtml("<font color='#616161'>Login terlebih dahulu untuk dapat mengirim komentar dan memberi rating. Belum punya akun? </font><font color='#0c0099'>daftar sekarang</font>")
        tv_login_desc.setOnClickListener {  }
        mProgressDialog = ProgressDialog(this@LoginActivity)
    }

    private fun doLogin(){
        btn_login.setOnClickListener {
            val email : String = et_email.text.toString().trim()
            val pass : String = et_password.text.toString().trim()
            if(!email.isEmpty() && !pass.isEmpty()){
                if(pass.length > 6){
                    mProgressDialog.setMessage("Sedang masuk")
                    mProgressDialog.setCancelable(false)
                    mProgressDialog.isIndeterminate = true
                    mProgressDialog.show()
                    val user : Call<WrappedResponse<User>> = userService.login(email, pass)
                    user.enqueue(object : Callback<WrappedResponse<User>>{
                        override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                            System.err.println("FENDAP - ${t.message}")
                            Toast.makeText(this@LoginActivity, "Terjadi kesalahan", Toast.LENGTH_LONG).show()
                            mProgressDialog.dismiss()
                        }
                        override fun onResponse(call: Call<WrappedResponse<User>>, response: Response<WrappedResponse<User>>) {
                            if(response.isSuccessful){
                                val body : WrappedResponse<User>? = response.body()
                                if(body != null){
                                    if(body.status == 1){
                                        Others.setLoggedIn(this@LoginActivity, body.data)
                                        finish()
                                    }else{
                                        Toast.makeText(this@LoginActivity, body.message, Toast.LENGTH_LONG).show()
                                    }
                                }
                            }else{
                                Toast.makeText(this@LoginActivity, "Tidak dapat berkoneksi dengan server. Coba lagi nanti", Toast.LENGTH_LONG).show()
                            }
                            mProgressDialog.dismiss()
                        }
                    })
                }else{
                    mProgressDialog.dismiss()
                    Toast.makeText(this@LoginActivity, "Password harus berisi minimal 6 karakter", Toast.LENGTH_LONG).show() }
            }else{
                mProgressDialog.dismiss()
                Toast.makeText(this@LoginActivity, "Mohon isi semua form terlebih dahulu", Toast.LENGTH_LONG).show() }
        }
    }
}
