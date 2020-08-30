package com.app.mateusz.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_flow_playground.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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

                    val flow = (1..3).asFlow().map { flowPlayground.requestFlow(1) }

                    flow.collect {
                        Log.d("TEST", "collected value $it")
                        it.collect {
                            Log.d("TEST", "collected value $it")
                        }
                    }

                }

                Log.d("TEST", "exceution time $executionTime")
            }.invokeOnCompletion { Log.d("TEST", "completed the coroutine") }
        }
    }


}
