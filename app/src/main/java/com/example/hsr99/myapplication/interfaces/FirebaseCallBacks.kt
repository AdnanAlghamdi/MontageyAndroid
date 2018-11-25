package com.example.hsr99.myapplication

import com.google.firebase.database.DataSnapshot

interface FirebaseCallBacks {
    fun onNewMessage(dataSnapshot: DataSnapshot?)
}