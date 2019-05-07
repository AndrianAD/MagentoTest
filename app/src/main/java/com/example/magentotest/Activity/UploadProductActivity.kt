package com.example.magentotest.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.magentotest.App
import com.example.magentotest.Retrofit.RetrofitFactory
import com.example.magentotest.Utils.toast
import com.example.magentotest.data.ImageForAdding.Content
import com.example.magentotest.data.ImageForAdding.Entry
import com.example.magentotest.data.ImageForAdding.ImageForAdding
import com.example.magentotest.data.Product.ProductList
import com.example.magentotest.data.ProductForAdding.ProductForAdding
import com.example.magentotest.data.ProductForAdding.ProductPojo
import kotlinx.android.synthetic.main.activity_upload_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*


class UploadProductActivity : AppCompatActivity() {
    var selectedImage: String = ""
    var position :Int=0

    private val PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.magentotest.R.layout.activity_upload_product)

        val retrofit = RetrofitFactory.retrofitInstance

        var products = App.productWithImages

        if (intent.hasExtra("position")) {
            position = intent.getStringExtra("position").toInt()
            et_name.setText(products.get(position).productRoom.name)
            et_price.setText(products.get(position).productRoom.price.toString())
        }


        button_OK.setOnClickListener {

            var product = ProductForAdding(
                name = et_name.text.toString(),
                price = et_price.text.toString().toInt(),
                sku = et_name.text.toString(),
                weight = Random().nextInt(100)
            )


            retrofit!!.addProduct(ProductPojo(product), "Bearer ${App.token}")
                .enqueue(object : Callback<ProductList> {
                    override fun onFailure(call: Call<ProductList>, t: Throwable) {
                        Log.e("retrofit", t.message)
                        toast("Error add Product")
                    }

                    override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                        Log.e("retrofit", response.body().toString())
                        finish()
                    }
                })

            var image = ImageForAdding(Entry(content = Content(selectedImage, "firstImage")))

            retrofit!!.addImage(product.sku, image, "Bearer ${App.token}").enqueue(object : Callback<Int> {
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("retrofit", t.message)
                    toast("Error add Image")
                }

                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    Log.e("retrofit", response.body().toString())
                    finish()
                }
            })

        }


        button_attach.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val imageUri: Uri = data.data
                var bitmap: Bitmap? = null
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                selectedImage = bitmapToBase64(bitmap!!)
                tv_attach_foto.setText("Foto is selected")
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
    }


}




