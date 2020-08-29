package com.app.mateusz.coroutine

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

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
        for (i in 1..20) {
            logThread("startEmitting", "execution attempt :$i before doSomeWork")
            doSomeWork()
            logThread("startEmitting", "execution attempt :$i after doSomeWork")
            emit("done")
        }
    }

    suspend fun doSomeWork() {
        withContext(Dispatchers.IO) {
            logThread("doSomeWork")
            delay(1000)
            logThread("doSomeWork")
        }
    }


}