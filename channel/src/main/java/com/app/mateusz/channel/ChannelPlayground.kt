package com.app.mateusz.channel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlin.coroutines.CoroutineContext

class ChannelPlayground : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + CoroutineName("channel-test")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_playground)

        launch {
            Log.d("TEST", interate().toString())
        }

    }

    suspend fun asyncSimulation(number: Int, callback: (Int) -> Unit) {
        delay(1000)
        callback(number * 10)
    }

    suspend fun interate(): ArrayList<Int> {
        val modifiedListOf = arrayListOf<Int>()

        val listOf = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val listOfIterator = listOf.iterator()

        while (listOfIterator.hasNext()) {
            asyncSimulation(listOfIterator.next()) {
                modifiedListOf.add(it)
            }
        }

        return modifiedListOf
    }

}
