package com.app.mateusz.coroutine

import com.app.mateusz.android.ThreadLogger.Companion.logThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class CoroutinePlayground {
   private fun valueEmitter(value: (Int) -> Unit) {
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
        .buffer(100)


}