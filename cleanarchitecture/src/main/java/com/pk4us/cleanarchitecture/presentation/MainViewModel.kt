package com.pk4us.cleanarchitecture.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pk4us.cleanarchitecture.data.ShopListRepositoryImpl
import com.pk4us.cleanarchitecture.domain.DeleteShopItemUseCase
import com.pk4us.cleanarchitecture.domain.EditShopItemUseCase
import com.pk4us.cleanarchitecture.domain.GetShopListUseCase
import com.pk4us.cleanarchitecture.domain.ShopItem

class MainViewModel:ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)

    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)

    }
}