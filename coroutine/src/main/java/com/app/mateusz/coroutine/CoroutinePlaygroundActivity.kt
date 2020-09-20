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

    private var childJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_playground)

        val job = launch {


            childJob = launch {
                try {
                    for (i in 1..10000) {
                        Log.d("TEST", "child1 job value: $i isActive $isActive")
                        if (i == 6000) {
                          throw Exception("something went wrong")
                        }
                    }
                } catch (exception: Exception) {
                    Log.d("TEST", "caught exception from child ${exception.localizedMessage}")
                }
                Log.d("TEST", "child1 executing done")
            }

            childJob = launch {
                for (i in 1..10000) {
                    Log.d("TEST", "child2 job value: $i isActive $isActive")
                }
                Log.d("TEST", "child2 executing done")
            }


            childJob = launch {
                for (i in 1..10000) {
                    Log.d("TEST", "child3 job value: $i isActive $isActive")
                }
                Log.d("TEST", "child3 executing done")
            }


            for (i in 1..10000) {
                Log.d("TEST", "parent job value: $i isActive: $isActive")
            }

            Log.d("TEST", "parent executing done - should not be executed, when child cancelled")
        }

    }

}
