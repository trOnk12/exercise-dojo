package com.app.mateusz.android

import android.util.Log

class ThreadLogger {
    companion object {

        fun logThread(source: String, message: String = "") {
            if (message.isEmpty()) {
                Log.d("TEST", "I am working on ${Thread.currentThread()}. called from $source")
            } else {
                Log.d("TEST", "I am working on :[${Thread.currentThread()}] \n called from: [$source]  \n message : $message]")
            }
        }

    }
}