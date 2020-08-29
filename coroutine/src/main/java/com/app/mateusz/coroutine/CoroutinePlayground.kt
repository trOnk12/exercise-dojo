package com.app.mateusz.coroutine

import android.util.Log
import kotlinx.coroutines.flow.flow

class CoroutinePlayground {
    companion object {
        fun logThread(source: String, message: String = "") {
            if (message.isEmpty()) {
                Log.d(
                    "TEST",
                    "I am working on ${Thread.currentThread()}. called from $source"
                )
            } else {
                Log.d(
                    "TEST",
                    "I am working on ${Thread.currentThread()}. called from $source  message to log : $message"
                )
            }
        }
    }

    fun startEmitting() = flow {
        for (i in 1..10000) {
            logThread("startEmitting()", "execution attempt :$i before emit()")
            emit("$i done")
            logThread("startEmitting()", "exceution attempt : $i after emit()")
        }
    }

}