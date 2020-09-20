package com.app.mateusz.concurrenctfilesynchronization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_concurrent_file_synchronization.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConcurrentFileSynchronizationActivity : AppCompatActivity() {
//
    private val fileOperator = FileOperator(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concurrent_file_synchronization)

        GlobalScope.launch {
            fileOperator.simulateReceiver {
                Log.d("TEST", "received list from cache $it")
            }
        }

        test.setOnClickListener {
            fileOperator.add("DUPA DUPA DUPA DUPA DUPA")
        }

        fileOperator.concurrentFileRead()
        fileOperator.concurrentFileRead1()

    }
}
