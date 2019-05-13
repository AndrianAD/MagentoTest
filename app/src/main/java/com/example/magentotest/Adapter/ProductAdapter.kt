package com.example.magentotest.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.bumptech.glide.Glide
import com.example.magentotest.Activity.DetailsActivity
import com.example.magentotest.Activity.UploadProductActivity
import com.example.magentotest.R
import com.example.magentotest.Room.Model.ProductWithImages
import com.example.magentotest.Utils.imageBaseURL
import com.example.magentotest.Utils.toast
import kotlinx.android.synthetic.main.recycler_view_element.view.*


class ProductAdapter(productList: List<ProductWithImages>) : RecyclerView.Adapter<ProductAdapter.UserViewHolder>() {
    var listProducts: List<ProductWithImages> = productList
    lateinit var context: Context

    fun setItemList(items: List<ProductWithImages>) {
        this.listProducts = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserViewHolder {
        context = parent.getContext()
        val view =
            LayoutInflater.from(context).inflate(R.layout.recycler_view_element, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listProducts.size
    }

    override fun onBindViewHolder(parent: UserViewHolder, position: Int) {


        val price = String.format("Price: %.2f$", listProducts.get(position).productRoom.price)
        parent.name.text = listProducts.get(position).productRoom.name
        parent.price.text = price

        var finalUrl: Any
        if (listProducts.get(position).images.size > 0) {
            var file: String = listProducts.get(position).images.get(0).pathImage
            finalUrl = "$imageBaseURL$file"

        } else {
            finalUrl = R.drawable.no_image_available
        }
        Log.i("Image", "$finalUrl")
        Glide.with(context)
            .load(finalUrl)
            .centerCrop()
            .error(R.drawable.no_image_available)
            .into(parent.imageView)

        parent.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_PRODUCT_SKU, listProducts.get(position).productRoom.sku)
            context.startActivity(intent)
        }

        parent.setting.setOnClickListener {

            val popupMenu = PopupMenu(context, parent.setting)
            val inflater = popupMenu.menuInflater
            inflater.inflate(R.menu.product_menu, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_product -> {
                        context.toast("Edit")
                        var intent = Intent(context, UploadProductActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_PRODUCT_SKU, listProducts.get(position).productRoom.sku)
                        context.startActivity(intent)

                    }
                    R.id.delete_product -> {
                        context.toast("Delete")
                    }


                }
                true
            }

        }
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name = view.tv_name
        var price = view.tv_price
        var imageView = view.imageView
        var setting = view.settings
    }
}


