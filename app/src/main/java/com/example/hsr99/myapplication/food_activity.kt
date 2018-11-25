package com.example.hsr99.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import com.example.hsr99.myapplication.adapters.StoresAdapter
import com.example.hsr99.myapplication.models.Store
import kotlinx.android.synthetic.main.content_food_activity.*
class food_activity : AppCompatActivity() {
    var adapter: StoresAdapter? = null
    var storesList = ArrayList<Store>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_food_activity)
        val storeView = stores_container

        storesList.add(Store("Burger Store", R.drawable.search1))
        storesList.add(Store("Burger Store 2", R.drawable.search2))
        storesList.add(Store("Kabsa Store", R.drawable.search3))
        storesList.add(Store("Kabsa Store 2",R.drawable.search4))
        storesList.add(Store("Casadia Store", R.drawable.search5))
        adapter = StoresAdapter(this, storesList)
        storeView.adapter = adapter
    }
}

