package com.example.magentotest

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.magentotest.Activity.UserActivity
import com.example.magentotest.Retrofit.RetrofitFactory
import com.example.magentotest.data.Credentials
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

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
                    intent.putExtra("Token", token)
                    startActivity(intent)
                }
            })
        }

    }
}
