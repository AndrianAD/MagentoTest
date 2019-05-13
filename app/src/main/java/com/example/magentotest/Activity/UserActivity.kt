package com.example.magentotest.Activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.magentotest.Activity.ViewModels.UserViewModel
import com.example.magentotest.Adapter.ProductAdapter
import com.example.magentotest.App
import com.example.magentotest.R
import com.example.magentotest.Utils.toast
import com.example.magentotest.data.CategorieForAdding.CategorieForAdding
import com.example.magentotest.data.CategorieForAdding.Category
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.dialog_new_category.*

class UserActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        App.token = intent.getStringExtra("Token")
        recyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel.getAllProduct("Room")
        userViewModel.getAllProduct("Retrofit")

        swipe_refresh.setOnRefreshListener {
            userViewModel.productDao.delleteAllImages()
            userViewModel.productDao.delleteAllProducts()
            userViewModel.getAllProduct("Retrofit")
            swipe_refresh.isRefreshing = false

        }

        val productAdapter = ProductAdapter(listOf())
        recyclerView.adapter = productAdapter


        userViewModel.productWithImage.observe(this, Observer {
            if (it != null) {
                productAdapter.setItemList(App.productWithImages)
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {

            R.id.add_new_product -> {
                val intent = Intent(this@UserActivity, UploadProductActivity::class.java)
                startActivity(intent)
            }

            R.id.add_new_category -> {
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Add new Category")
                    .setView(R.layout.dialog_new_category)
                    .create()
                dialog.show()

                dialog.button_save_category.setOnClickListener {
                    var newCategory = Category()
                    if (!dialog.et_add_name.text.isNullOrEmpty()) {
                        newCategory.name = dialog.et_add_name.text.toString()
                        userViewModel.insertCategory(CategorieForAdding(newCategory))
                        userViewModel.callbackAddingCategory.observe(
                            this,
                            Observer {
                                if (it == true) {
                                    toast("Category is added")
                                    dialog.dismiss()
                                } else {
                                    toast("ERROR to add Category")
                                    dialog.dismiss()
                                }
                            })
                    } else {
                        toast("Please choose the name")
                        return@setOnClickListener
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

