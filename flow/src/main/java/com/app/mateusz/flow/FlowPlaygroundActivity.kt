package com.app.mateusz.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_flow_playground.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class FlowPlaygroundActivity : AppCompatActivity() {

    private val flowPlayground = FlowPlayground()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_playground)

        button.setOnClickListener {
            GlobalScope.launch {
                val executionTime = measureTimeMillis {
                    val flow = flowPlayground.emitValues()

                    try {
                        flow.collectLatest {
                            Log.d("TEST", "collected value $it")
                            delay(200)
                            Log.d("TEST", "done processing collected value")
                        }
                    } catch (exception: Exception) {
                        Log.d("TEST", "exception has been thrown $exception")
                    }
                }

                Log.d("TEST", "exceution time $executionTime")
            }.invokeOnCompletion { Log.d("TEST", "completed the coroutine") }
        }
    }


}
