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
        get() = Dispatchers.Default + Job()

    private var childJOb: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_playground)

        val supervisor = SupervisorJob()
        launch {
            with(CoroutineScope(coroutineContext + supervisor)) {

                try {
                    supervisorScope {

                        launch {
                            while (isActive) {
                                Log.d("TEST", "logging child1")
                                delay(1000)

                                throw Exception()
                            }
                        }

                        launch {
                            while (isActive) {
                                Log.d("TEST", "logging child1")
                                delay(1000)
                            }
                        }
                    }
                } catch (exception: Exception) {
                    Log.d("TEST", "exception thrown")
                }

            }

            button.setOnClickListener {
            }

        }
    }

}
