package com.app.mateusz.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowPlayground {

    fun emitValues() = flow {
        for (i in 1..10) {
            delay(100)
            emit(i)
        }
    }

}