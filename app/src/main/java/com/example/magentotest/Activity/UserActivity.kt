package com.example.magentotest.Activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.magentotest.Adapter.ProductAdapter
import com.example.magentotest.App
import com.example.magentotest.ProductViewModel
import com.example.magentotest.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by lazy {
        ViewModelProviders.of(this).get(ProductViewModel::class.java)
    }
    lateinit var tokenObserver: Observer<String>
    lateinit var listofProductsObserver: Observer<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        productViewModel.tokenLIVE.value = intent.getStringExtra("Token")
        App.token = intent.getStringExtra("Token")
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipe_refresh.setOnRefreshListener {
                productViewModel.productDao.delleteAllImages()
                productViewModel.productDao.delleteAllProducts()
            productViewModel.getAllProduct("Retrofit")
            swipe_refresh.isRefreshing = false

        }


        tokenObserver = Observer {
                productViewModel.getAllProduct("Room")
                productViewModel.getAllProduct("Retrofit")
        }

        val productAdapter = ProductAdapter(listOf())
        recyclerView.adapter = productAdapter

        listofProductsObserver = Observer {
            if (it != null) {
                productAdapter.setItemList(App.productWithImages)
            }

        }

        productViewModel.tokenLIVE.observe(this, tokenObserver)
        productViewModel.productWithImage.observe(this, listofProductsObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {

            R.id.add_new -> {
                var intent = Intent(this@UserActivity, UploadProductActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
