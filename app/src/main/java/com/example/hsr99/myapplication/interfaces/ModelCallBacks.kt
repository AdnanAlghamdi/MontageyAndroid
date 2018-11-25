package com.example.hsr99.myapplication

import com.example.hsr99.myapplication.ChatPojo
import java.util.ArrayList

interface ModelCallBacks {
    fun onModelUpdated(messages: ArrayList<ChatPojo>)
}
