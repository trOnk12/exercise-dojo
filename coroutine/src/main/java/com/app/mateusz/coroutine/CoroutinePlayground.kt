package com.app.mateusz.coroutine

import android.util.Log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
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

    fun valueEmitter(value: (Int) -> Unit) {
        for (i in 1..10000) {
          //  logThread("valueEmitter()", "execution attempt :$i ")
            value(i)
        }
    }

    fun startEmitting() = callbackFlow {
        valueEmitter {
            val offerResult = offer(it)
            if (offerResult == true) {
                Log.d("TEST", "buffer is not overflown value is $it")
            }

        }
        awaitClose { cancel() }
    }


}