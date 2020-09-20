package com.app.mateusz.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_coroutine_playground.*
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class CoroutinePlaygroundActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_playground)

        val job = launch {
            try {
                for (i in 1..100000) {
                    try {
                        ensureActive()
                        Log.d("TEST", "value: $i on thread ${Thread.currentThread()}")
                    }catch (exception :Exception){
                        Log.d("TEST", "exception is thrown ${exception.localizedMessage}")
                    }
                }
            } catch (exception: Exception) {
                Log.d("TEST", "exception is thrown ${exception.localizedMessage}")
            }
        }

        job.invokeOnCompletion { Log.d("TEST", "job completed with ${it?.localizedMessage}") }

        button.setOnClickListener {
            job.cancel()
        }

    }

}
