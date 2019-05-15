package com.example.magentotest.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.magentotest.Model.Credentials
import com.example.magentotest.R
import com.example.magentotest.Retrofit.RetrofitFactory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = RetrofitFactory.retrofitInstance


        bntSignIn.setOnClickListener {
//            if (etUsername.text.isNullOrEmpty() && etPassword.text.isNullOrEmpty()) {
//                toast("Empty fields")
//                return@setOnClickListener
//            }
//            var credentials =
//                Credentials(etUsername.text.toString(), etPassword.text.toString())

            retrofit!!.authentication(Credentials("admin","ImproveIT0")).enqueue(object : retrofit2.Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.v("retrofit", "Erorr $t.message")
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.v("retrofit", response.message())
                    token = response.body() ?: "null"
                    var intent= Intent(this@MainActivity, UserActivity::class.java)
                    intent.putExtra(UserActivity.EXTRA_TOKEN, token)
                    startActivity(intent)
                }
            })
        }

    }
}
