package com.app.mateusz.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_coroutine_playground.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CoroutinePlaygroundActivity : AppCompatActivity() {

    private val coroutinePlayground = CoroutinePlayground()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_playground)

        button.setOnClickListener {
            GlobalScope.launch {
                coroutinePlayground.startEmitting().collect {
                    CoroutinePlayground.logThread("collect()", "before doSomeWork()")
                    doSomework()
                    CoroutinePlayground.logThread("collect()", "collected value: $it")
                }
            }
        }

    }

    private suspend fun doSomework() {
        CoroutinePlayground.logThread("doSomeWork()", "before delay()")
        delay(2000)
        CoroutinePlayground.logThread("doSomeWork()", "after delay()")
    }
}


