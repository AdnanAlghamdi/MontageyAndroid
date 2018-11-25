package com.example.hsr99.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.hsr99.myapplication.adapters.StoresAdapter
import com.example.hsr99.myapplication.models.Store
import kotlinx.android.synthetic.main.handcraft_activity.*

class handcraft : AppCompatActivity() {
    var adapter: StoresAdapter? = null
    var storesList = ArrayList<Store>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.handcraft_activity)
        val storeView = stores_container
        storesList.add(Store("Sew store", R.drawable.handcraft1))
        storesList.add(Store("Earpods styling", R.drawable.handcraft2))
        storesList.add(Store("Necklaces", R.drawable.handcraft3))
        adapter = StoresAdapter(this, storesList)
        storeView.adapter = adapter
    }
}
