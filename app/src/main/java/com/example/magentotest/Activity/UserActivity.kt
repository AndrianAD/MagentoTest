package com.example.magentotest.Activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.magentotest.Adapter.ProductAdapter
import com.example.magentotest.App
import com.example.magentotest.ProductViewModel
import com.example.magentotest.R
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by lazy {
        ViewModelProviders.of(this).get(ProductViewModel::class.java)
    }
    lateinit var listofProductsObserver: Observer<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        App.token = intent.getStringExtra("Token")
        recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel.getAllProduct("Room")
        productViewModel.getAllProduct("Retrofit")

        swipe_refresh.setOnRefreshListener {
            productViewModel.productDao.delleteAllImages()
            productViewModel.productDao.delleteAllProducts()
            productViewModel.getAllProduct("Retrofit")
            swipe_refresh.isRefreshing = false

        }

        val productAdapter = ProductAdapter(listOf())
        recyclerView.adapter = productAdapter

        listofProductsObserver = Observer {
            if (it != null) {
                productAdapter.setItemList(App.productWithImages)
            }

        }
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
