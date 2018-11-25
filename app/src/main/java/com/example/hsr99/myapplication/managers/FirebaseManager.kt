package com.example.hsr99.myapplication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import com.example.hsr99.myapplication.FirebaseCallBacks
import java.util.HashMap

/**
 * Created by Sam on 12/04/18.
 */
class FirebaseManager private constructor(roomName: String, private var mCallbacks: FirebaseCallBacks?) : ChildEventListener {
    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
        mCallbacks!!.onNewMessage(p0!!)
    }

    private val mMessageReference: DatabaseReference
    //private val key : String
    var chatname:String = ""


    init {
       // key = FirebaseAuth.getInstance().currentUser!!.uid
       // mMessageReference = FirebaseDatabase.getInstance().reference.child("channels").child(key).child("thread")
       // FirebaseDatabase.getInstance().reference.child("channels").child(key).child("name").setValue(roomName)
        //brbsh



        //mMessageReference = FirebaseDatabase.getInstance().reference.child("channels").child(chatname).child("thread")
        //FirebaseDatabase.getInstance().reference.child("channels").child(roomName).child("name").setValue(roomName)
        //end brbsh
        mMessageReference = FirebaseDatabase.getInstance().reference.child("channels").child("-LOikMerxtQIQmDZJySP").child("thread")
        //FirebaseDatabase.getInstance().reference.child("channels").child(roomName).child("name").setValue(roomName)

    }

    fun addMessageListeners() {
        mMessageReference.addChildEventListener(this)
    }

    fun removeListeners() {
        mMessageReference.removeEventListener(this)
    }

    //override fun onChildAdded(dataSnapshot: DataSnapshot?, s: String?) {
      //  mCallbacks!!.onNewMessage(dataSnapshot!!)
    //}

    override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

    }

    override fun onChildRemoved(dataSnapshot: DataSnapshot) {

    }

    override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

    }

    override fun onCancelled(databaseError: DatabaseError) {

    }

    fun sendMessageToFirebase(message: String) {
        val map = HashMap<String, Any>()
        map["text"] = message
        //map["time"] = System.currentTimeMillis()
        map["senderId"] = FirebaseAuth.getInstance().currentUser!!.uid
        val keyToPush = mMessageReference.push().key
        mMessageReference.child(keyToPush!!).setValue(map)
    }

    fun destroy() {
        sFirebaseManager = null
        mCallbacks = null
    }

    companion object {
        @Volatile
        private var sFirebaseManager: FirebaseManager? = null

        @Synchronized
        fun getInstance(roomName: String, callBacks: FirebaseCallBacks): FirebaseManager? {
            if (sFirebaseManager == null) {
                synchronized(FirebaseManager::class.java) {
                    sFirebaseManager = FirebaseManager(roomName, callBacks)
                }
            }
            return sFirebaseManager
        }
    }
}
