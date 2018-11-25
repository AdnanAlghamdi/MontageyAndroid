package com.example.hsr99.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.store_activity.*

class Store : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.store_activity)
        food_stores.setOnClickListener{
            directToFood();
        }
        services_stores.setOnClickListener{
            directToService()
        }
        handcraft_stores.setOnClickListener {
            directToHandcraft()
        }

    }
    private fun directToFood(){
        val intent = Intent(this, food_activity::class.java)
        startActivity(intent)
    }
    private fun directToService(){
        val intent = Intent(this, service::class.java)
        startActivity(intent)
    }
    private fun directToHandcraft(){
        val intent = Intent(this, handcraft::class.java)
        startActivity(intent)
    }

}
