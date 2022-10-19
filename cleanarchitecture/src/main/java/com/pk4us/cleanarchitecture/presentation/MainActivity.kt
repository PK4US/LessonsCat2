package com.pk4us.cleanarchitecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.pk4us.cleanarchitecture.R
import com.pk4us.cleanarchitecture.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var llshopList :  LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llshopList = findViewById(R.id.ll_shop_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list:List<ShopItem>){
        llshopList.removeAllViews()
        for(shopItem in list){
            val layout = if (shopItem.enabled){
                R.layout.item_shop_enabled
            }else{
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater.from(this).inflate(layout,llshopList,false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            view.setOnClickListener{
                viewModel.changeEnableState(shopItem)
                true
            }
            llshopList.addView(view)
        }
    }
}