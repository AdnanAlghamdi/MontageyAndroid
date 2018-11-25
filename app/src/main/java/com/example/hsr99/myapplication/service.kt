package com.example.hsr99.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.hsr99.myapplication.adapters.StoresAdapter
import com.example.hsr99.myapplication.models.Store
import kotlinx.android.synthetic.main.service_activity.*

class service : AppCompatActivity() {
    var adapter: StoresAdapter? = null
    var storesList = ArrayList<Store>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.service_activity)
        val storeView = stores_container
        storesList.add(Store("Photographer", R.drawable.services1))
        storesList.add(Store("CV writing", R.drawable.services2))
        adapter = StoresAdapter(this, storesList)
        storeView.adapter = adapter
    }
}
