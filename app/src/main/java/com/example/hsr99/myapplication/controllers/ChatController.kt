package com.example.hsr99.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.google.firebase.database.DataSnapshot
import kotlinx.android.synthetic.main.activity_chat.view.*
import com.example.hsr99.myapplication.R
import com.example.hsr99.myapplication.ChatAdapter
import com.example.hsr99.myapplication.FirebaseCallBacks
import com.example.hsr99.myapplication.ModelCallBacks
import com.example.hsr99.myapplication.FirebaseManager
import com.example.hsr99.myapplication.ChatPojo
import com.example.hsr99.myapplication.MessageModel
import com.example.hsr99.myapplication.MyUtils
import java.util.ArrayList

/**
 * Created by Sam on 11/04/18.
 */
class ChatController : AppCompatActivity(), View.OnClickListener, FirebaseCallBacks, ModelCallBacks {

    private var mEdittextChat: EditText? = null
    private var mRecyclerView: RecyclerView? = null
    private var mRoomName: String? = null
    private var mModel: MessageModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)



        mRoomName = intent.getStringExtra(MyUtils.EXTRA_ROOM_NAME)

        mModel = MessageModel(this)
        setListener(mRoomName!!)

        mEdittextChat = findViewById<EditText>(R.id.edittext_chat_message)
        mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)

        findViewById<View>(R.id.button_send_message).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_send_message ->
                sendMessageToFirebase(mRoomName!!, mEdittextChat!!.text.toString())
        }
    }

    private fun updateList(list: ArrayList<ChatPojo>) {
        val chatAdapter = ChatAdapter(this, list)
        mRecyclerView!!.adapter = chatAdapter
        mRecyclerView!!.scrollToPosition(list.size - 1)
    }

    override fun onNewMessage(dataSnapshot: DataSnapshot?) {
        mModel!!.addMessages(dataSnapshot!!)
    }

    override fun onModelUpdated(messages: ArrayList<ChatPojo>) {
        if (messages.size > 0) {
            updateList(messages)
        }
    }

    private fun sendMessageToFirebase(roomName: String, message: String) {
        if (!message.trim().equals("")) {
            FirebaseManager.getInstance(roomName, this)!!.sendMessageToFirebase(message)
        }
        mEdittextChat!!.setText("")
    }

    private fun setListener(roomName: String) {
        FirebaseManager.getInstance(roomName, this)!!.addMessageListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        FirebaseManager.getInstance(mRoomName!!, this)!!.removeListeners()
        FirebaseManager.getInstance(mRoomName!!, this)!!.destroy()
    }
}