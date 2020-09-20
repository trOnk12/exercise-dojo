package com.app.mateusz.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_coroutine_playground.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutinePlaygroundActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_playground)

       val job = launch {
           withContext(Dispatchers.Default) {

               for (i in 1..1000000) {
                   ensureActive()
                       Log.d("TEST", "value: $i on thread ${Thread.currentThread()}")
                   }

           }
        }

        job.invokeOnCompletion { Log.d("TEST","job completed with ${it?.localizedMessage}") }

        button.setOnClickListener {
            job.cancel()
        }

    }

}
