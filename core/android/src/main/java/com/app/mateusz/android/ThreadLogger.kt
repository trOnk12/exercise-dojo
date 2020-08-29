package com.app.mateusz.android

import android.util.Log

class ThreadLogger {
    companion object {

        fun logThread(source: String, message: String = "") {
            if (message.isEmpty()) {
                Log.d("TEST", "\n I am working on:[${Thread.currentThread()}] \n Called from: [$source]")
            } else {
                Log.d("TEST", "\n I am working on:[${Thread.currentThread()}] \n Called from: [$source]  \n Message : [$message]")
            }
        }

    }
}