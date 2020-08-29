package com.app.mateusz.dsl

data class SausageDog(
    var name: String? = "",
    var age: Int? = 0,
    var color: String? = "",
    var length: Double? = 0.00
)

fun sausagesDog(block: SausageDog.() -> Unit) = SausageDog().apply(block)
