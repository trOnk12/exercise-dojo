package com.app.mateusz.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_coroutine_playground.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CoroutinePlaygroundActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_playground)

       val job = launch {
            for(i in 1..1000000)
            Log.d("TEST","value: $i on thread ${Thread.currentThread()}")
        }

        button.setOnClickListener {
            job.cancel()
        }

    }

}
