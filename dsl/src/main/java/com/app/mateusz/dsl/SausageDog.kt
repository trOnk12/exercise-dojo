package com.app.mateusz.dsl

data class SausageDog(
    var dogAppearence: DogAppearence? = null,
    var owner: Owner? = null
)

data class DogAppearence(
    var name: String? = "",
    var age: Int? = 0,
    var color: String? = "",
    var length: Double? = 0.00,
    var owner: Owner? = null
)

data class Owner(var name: String? = "", var country: String? = "")

fun sausagesDog(block: SausageDog.() -> Unit) = SausageDog().apply(block)

fun SausageDog.owner(block: Owner.() -> Unit) {
    owner = Owner().apply(block)
}

fun SausageDog.dogAppearance(block: DogAppearence.() -> Unit) {
    dogAppearence = DogAppearence().apply(block)
}