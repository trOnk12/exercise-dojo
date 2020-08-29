package com.app.mateusz.dsl

data class SausageDog(
    val dogAppearance: DogAppearance? = null,
    val owner: Owner? = null
)

data class DogAppearance(
    val name: String? = "",
    val age: Int? = 0,
    val color: Color? = Color.BROWN,
    val length: Double? = 0.00
)

enum class Color(name: String) {
    BROWN("brown"),
    BLACK("black")
}

data class Owner(var name: String? = "", var country: String? = "")

fun sausagesDog(block: SausageDogBuilder.() -> Unit) = SausageDogBuilder().apply(block).build()

class SausageDogBuilder {

    private var dogAppearance: DogAppearance? = null
    private var owner: Owner? = null

    fun owner(block: OwnerBuilder.() -> Unit) {
        owner = OwnerBuilder().apply(block).build()
    }

    fun dogAppearance(block: DogAppearanceBuilder.() -> Unit) {
        dogAppearance = DogAppearanceBuilder().apply(block).build()
    }

    fun build(): SausageDog {
        return SausageDog(dogAppearance, owner)
    }

}

class DogAppearanceBuilder {

    var name: String? = ""
    var age: Int? = 0
    var color: Color = Color.BROWN
    var length: Double? = 00.00

    fun build(): DogAppearance {
        return DogAppearance(name, age, color, length)
    }

}

class OwnerBuilder {

    var name: String? = ""
    var country: String? = ""

    fun build(): Owner {
        return Owner(name, country)
    }

}