package com.example.magentotest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomAPI : BaseAPI {


    override fun getAllProduct(viewModel: ProductViewModel) {

            viewModel.productsRoomLIVE.postValue(viewModel.productDao.getAll())

    }


}