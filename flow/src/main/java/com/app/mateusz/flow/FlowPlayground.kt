package com.app.mateusz.flow

import com.app.mateusz.android.ThreadLogger.Companion.logThread
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowPlayground {

    fun emitValues() = flow {
        for (i in 1..10) {
            delay(100)
            logThread("emitValues()")
            emit(i)
        }
    }

}