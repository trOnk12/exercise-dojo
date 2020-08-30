package com.app.mateusz.flow

import com.app.mateusz.android.ThreadLogger.Companion.logThread
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowPlayground {

    fun emitValues() = flow {
        for (i in 1..5) {
            logThread("emitValues()")
            emit(i)
        }
    }

    fun emitStrings() = flow {
        for (i in 10..15) {
            delay(2000)
            logThread("emitStrings()")
            emit("I am emitting $i")
        }
    }

}