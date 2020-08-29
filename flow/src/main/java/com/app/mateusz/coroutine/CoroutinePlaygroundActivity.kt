package com.app.mateusz.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.app.mateusz.android.ThreadLogger.Companion.logThread
import kotlinx.android.synthetic.main.activity_coroutine_playground.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class CoroutinePlaygroundActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val coroutinePlayground = CoroutinePlayground()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_playground)

        button.setOnClickListener {
            coroutineScope.launch {
                coroutinePlayground.startEmitting().collect {
                    logThread("collect()", "before doSomeWork()")
                    doSomework()
                    logThread("collect()", "collected value: $it")
                }
            }
        }

        button2.setOnClickListener {
            coroutineScope.cancel()
        }

    }

    private suspend fun doSomework() {
        logThread("doSomeWork()", "before delay()")
        delay(1000)
        logThread("doSomeWork()", "after delay()")
    }

}


