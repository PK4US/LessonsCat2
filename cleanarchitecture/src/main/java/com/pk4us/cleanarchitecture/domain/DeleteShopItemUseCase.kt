package com.pk4us.cleanarchitecture.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository){
    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}
