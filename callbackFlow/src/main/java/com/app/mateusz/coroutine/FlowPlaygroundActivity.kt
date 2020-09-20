package com.app.mateusz.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.app.mateusz.android.ThreadLogger.Companion.logThread
import kotlinx.android.synthetic.main.activity_flow_playground.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class FlowPlaygroundActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val coroutinePlayground = FlowPlayground()

    private val adapter = Adapter()

    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_playground)

        rv.adapter = adapter

        button.setOnClickListener {
            job = coroutineScope.launch {
                val flow = coroutinePlayground.startEmitting()

                flow.collect {
                    logThread("collect()", "before doSomeWork()")
                    doSomework()
                    logThread("collect()", "collected value: $it")

                    withContext(Dispatchers.Main) {
                        adapter.dataSet.add("collected value: $it")
                        adapter.notifyDataSetChanged()
                    }
                }

                logThread("try -> launch()", "after collect cancelling should not be executed")

            }

            job.invokeOnCompletion {
                runOnUiThread { Toast.makeText(this, "Job is cancelled", Toast.LENGTH_LONG).show() }
            }

        }

        button2.setOnClickListener {
            job.cancel()
        }

    }

    private suspend fun doSomework() {
        logThread("doSomeWork()", "before delay()")
        delay(1000)
        logThread("doSomeWork()", "after delay()")
    }

}


