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
                    val flow1 = flowPlayground.emitValues()
                    val flow2 = flowPlayground.emitStrings()

                    flow1.zip(flow2) { a, b -> "$a -> $b" } // compose a single string
                        .collect { Log.d("TEST", "result is $it") } // collect and printcc

                }

                Log.d("TEST", "exceution time $executionTime")
            }.invokeOnCompletion { Log.d("TEST", "completed the coroutine") }
        }
    }


}
