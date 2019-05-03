package com.example.magentotest

class RoomAPI : BaseAPI {


    override fun getAllProduct(viewModel: ProductViewModel) {
        viewModel.productsRoomLIVE.postValue(viewModel.productDao.getAll())
    }


}