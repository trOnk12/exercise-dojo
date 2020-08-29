package com.app.mateusz.coroutine

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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
        for (i in 1..100000) {
            logThread("valueEmitter()", "execution attempt :$i ")
            value(i)
        }
    }

    fun startEmitting() = callbackFlow<Int> {
        valueEmitter {
            logThread("startEmitting()", "before sendBloccking(), value: $it ")
            sendBlocking(it)
            logThread("startEmitting()", "after sendBloccking(), value: $it ")
        }
        awaitClose { cancel() }
    }.flowOn(Dispatchers.Default)
        .buffer(Channel.UNLIMITED)


}