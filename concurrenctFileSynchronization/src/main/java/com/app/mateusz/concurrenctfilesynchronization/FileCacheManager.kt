package com.app.mateusz.concurrenctfilesynchronization

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import java.io.*
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FileCacheManager(private val context: Context) {

    var service: ExecutorService = Executors.newSingleThreadExecutor()

    fun writeToFile(
        data: String
    ) {
        service.submit {
         //   Log.d("TEST", "writing to file $data")
            try {
                val outputStreamWriter = OutputStreamWriter(
                    context.openFileOutput(
                        "config.txt",
                        Context.MODE_PRIVATE
                    )
                )
                outputStreamWriter.write(data)
                outputStreamWriter.close()
            } catch (e: IOException) {
                Log.e("Exception", "File write failed: " + e.toString())
            }
        }
    }

    fun readFromFile() =
        service.submit(Callable<String> {
            var ret = ""
            try {
                val inputStream: InputStream? = context.openFileInput("config.txt")
                if (inputStream != null) {
                    val inputStreamReader = InputStreamReader(inputStream)
                    val bufferedReader = BufferedReader(inputStreamReader)
                    var receiveString: String? = ""
                    val stringBuilder = StringBuilder()
                    while (bufferedReader.readLine().also({ receiveString = it }) != null) {
                        stringBuilder.append("\n").append(receiveString)
                    }
                    inputStream.close()
                    ret = stringBuilder.toString()
                }
            } catch (e: FileNotFoundException) {
                Log.e("login activity", "File not found: " + e.toString())
            } catch (e: IOException) {
                Log.e("login activity", "Can not read file: " + e.toString())
            }
            Log.d("TEST",ret)
            ret

        }).get()

}