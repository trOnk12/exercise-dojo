package com.app.mateusz.concurrenctfilesynchronization

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn


class FileOperator(private val context: Context) {

    private val fileCacheManager = FileCacheManager(context)

    //if buffer is full this callback waits for execution and is blocked
    private fun simulateCallback(callback: (Int) -> Unit) {
        for (i in 1..10) {
            callback(i)
        }
    }

    fun concurrentFileRead() {
        for (i in 10..15) {
            val fileContent = fileCacheManager.readFromFile()
            val newContent = fileContent + "value from concurrentFileRead: $i"
            fileCacheManager.writeToFile(newContent)
        }
    }

    fun concurrentFileRead1() {
        for (i in 15..20) {
            val fileContent = fileCacheManager.readFromFile()
            val newContent = fileContent + "value from concurrentFileRead1: $i"
            fileCacheManager.writeToFile(newContent)
        }
    }

    private fun simulateFlow() = callbackFlow<Int> {
        simulateCallback {
            sendBlocking(it)
        }
        awaitClose { cancel() }
    }.buffer(5)
        .flowOn(Dispatchers.Default)

    //when buffer is full and value gets consumed callback is deblocked and it sends value it was blocked on, which is in the past
    suspend fun simulateReceiver(receiverCallback: (List<String>) -> Unit) {
        val flow = simulateFlow()
        flow.collect {
            delay(1000)
            val fileContent = fileCacheManager.readFromFile()
            val newContent = fileContent + "value from simulateReceiver: $it"
            fileCacheManager.writeToFile(newContent)
            //read cache
            //update cache
            //write cache
        }
    }

    fun add(s: String) {
        val fileContent = fileCacheManager.readFromFile()
        val newContent = fileContent + s
        fileCacheManager.writeToFile(newContent)
    }


}