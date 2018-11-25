package com.example.hsr99.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.hsr99.myapplication.R
import kotlinx.android.synthetic.main.store_item.view.*
import android.content.Context
import com.example.hsr99.myapplication.models.Store


class StoresAdapter: BaseAdapter {
    var foodsList = ArrayList<Store>()
    var context: Context? = null
    constructor(context: Context, foodsList: ArrayList<Store>) : super() {
        this.context = context
        this.foodsList = foodsList
    }

    override fun getCount(): Int {
        return foodsList.size
    }

    override fun getItem(position: Int): Any {
        return foodsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val food = this.foodsList[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var foodView = inflator.inflate(R.layout.store_item, null)
        foodView.store_img.setImageResource(food.image!!)
        foodView.store_name.text = food.name!!

        return foodView
    }


}