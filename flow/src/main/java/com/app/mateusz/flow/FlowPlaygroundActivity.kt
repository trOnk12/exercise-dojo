package com.app.mateusz.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_flow_playground.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FlowPlaygroundActivity : AppCompatActivity() {

    private val flowPlayground = FlowPlayground()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_playground)

        button.setOnClickListener {
            GlobalScope.launch {
                val flow = flowPlayground.emitValues()
                try {
                    flow.collect {

                        Log.d("TEST", "collected value $it")
                        if (it == 2) {
                            cancel()
                        }

                    }
                } catch (exception: Exception) {
                    Log.d("TEST", "exception thrown ${exception.localizedMessage}")
                }
            }
        }
    }


}
