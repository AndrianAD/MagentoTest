package com.example.magentotest.Adapter


import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.magentotest.Activity.DetailsActivity
import com.example.magentotest.R
import com.example.magentotest.Room.Model.ProductRoom
import kotlinx.android.synthetic.main.recycler_view_element.view.*


class ProductAdapter(productList: List<ProductRoom>) : RecyclerView.Adapter<ProductAdapter.UserViewHolder>() {
    var listProducts: List<ProductRoom> = productList
    lateinit var context: Context

    fun setItemList(items: List<ProductRoom>) {
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

        parent.name.text = let { listProducts.get(position).name}
        parent.price.text = let { listProducts.get(position).price.toString() }




//        var finalUrl: Any
//        if(listProducts.isNotEmpty()) {
//            var file: String = listProducts.items.get(position).media_gallery_entries.get(0).file
//            finalUrl = "$imageBaseURL$file"
//
//        }else{
//            finalUrl = R.drawable.no_image_available
//        }
//        Glide.with(context)
//            .load(finalUrl)
//            .error(R.drawable.no_image_available)
//            .into(parent.imageView)



        parent.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("position", position.toString())
            context.startActivity(intent)
        }

//        fun getListOfAllImageFromProductRoom(value: ProductRoom): List<String>{
//            return value.image.split("!").toMutableList()
//        }

    }


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name = view.tv_name
        var price = view.tv_price
        var imageView = view.imageView
    }
}


