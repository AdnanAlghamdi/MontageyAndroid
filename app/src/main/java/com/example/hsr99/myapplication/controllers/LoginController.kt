package com.example.hsr99.myapplication

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.example.hsr99.myapplication.R
import com.example.hsr99.myapplication.MyUtils
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.store_activity.*

class LoginController : AppCompatActivity(), View.OnClickListener  {

    private var mAuth: Button? = null
    private var mCreateRoom: Button? = null
    private var mEnterExistingRoom: Button? = null
    private var mInfo: TextView? = null
    private var mChatRoomDialog: Dialog? = null
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialise views
        mAuth = findViewById<Button>(R.id.button_chat)
        mCreateRoom = findViewById<Button>(R.id.button_create_room)
        mEnterExistingRoom = findViewById<Button>(R.id.button_existing_room)
        mInfo = findViewById<TextView>(R.id.text_info)
        //chatList
//
//        val list = ArrayList<String>()
//        var adapterlist : ArrayAdapter<String>?=null
//        list.add("Hello")
//        list.add("Hello 1")
//        list.add("Hello 2")
//        adapterlist = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
//        listView.adapter = adapterlist
       //End of chat List


        // set click listener to views
        mAuth!!.setOnClickListener(this)
        mCreateRoom!!.setOnClickListener(this)
        mEnterExistingRoom!!.setOnClickListener(this)
        //authSuccessful()
        button_chat.setOnClickListener {
            authSuccessful()
        }
        button_store.setOnClickListener {
            val intent = Intent(this,Store::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {         // equivalent to switch in java
            R.id.button_chat ->
                firebaseAnonymousAuth()
            R.id.button_create_room ->
                showRoomDialog()
            R.id.button_existing_room ->
                showRoomDialog()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun authSuccessful() {
        mAuth!!.visibility = View.GONE
        mCreateRoom!!.visibility = View.VISIBLE
        mEnterExistingRoom!!.visibility = View.VISIBLE
        mInfo!!.visibility = View.VISIBLE
    }

    private fun showRoomDialog() {
        mChatRoomDialog = Dialog(this)
        mChatRoomDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_room, null)
        val submitRoomName = view.findViewById<Button>(R.id.button_room_submit)
        val editTextRoomName = view.findViewById<EditText>(R.id.edittext_room_name)
        submitRoomName.setOnClickListener {
            if (editTextRoomName.text.toString().trim().isEmpty()) {
                showToast("Enter a valid Name")
            } else {
                startChatActivity(editTextRoomName.text.toString())
            }
        }

        mChatRoomDialog!!.setContentView(view)
        mChatRoomDialog!!.show()

    }

    private fun firebaseAnonymousAuth() {
      /*  mAuth!!.text = getString(R.string.text_waiting_for_auth)
        FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        showToast("Firebase authentication failed, please check your internet connection")
                        mAuth!!.text = getString(R.string.authentication_label)
                    } else {
                        authSuccessful()
                    }
                }
    */





    }

    private fun startChatActivity(roomName: String) {
        mChatRoomDialog!!.dismiss()
        mChatRoomDialog = null

        val intent = Intent(this, ChatController::class.java)
        intent.putExtra(MyUtils.EXTRA_ROOM_NAME, roomName)
        startActivity(intent)
    }
}
