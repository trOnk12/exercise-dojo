package com.app.mateusz.dsl

data class SausageDog(
    val dogAppearance: DogAppearance?,
    val owner: List<Owner>
)

data class DogAppearance(
    val name: String,
    val age: Int,
    val color: Color,
    val length: Double
)

enum class Color(name: String) {
    BROWN("brown"),
    BLACK("black")
}

data class Owner(var name: String, var country: String)

fun sausagesDog(block: SausageDogBuilder.() -> Unit) = SausageDogBuilder().apply(block).build()

class SausageDogBuilder {

    private var dogAppearance: DogAppearance? = null

    private var owners = mutableListOf<Owner>()

    fun owner(block: OwnerBuilder.() -> Unit) {
        owners.add(OwnerBuilder().apply(block).build())
    }

    fun owners(block: OWNERS.() -> Unit) {
        owners.addAll(OWNERS().apply(block))
    }

    fun dogAppearance(block: DogAppearanceBuilder.() -> Unit) {
        dogAppearance = DogAppearanceBuilder().apply(block).build()
    }

    fun build(): SausageDog {
        return SausageDog(dogAppearance, owners)
    }

}

class OWNERS : ArrayList<Owner>() {

    fun owner(block: OwnerBuilder.() -> Unit) {
        add(OwnerBuilder().apply(block).build())
    }

}

class DogAppearanceBuilder {

    var name: String = ""
    var age: Int = 0
    var color: Color = Color.BROWN
    var length: Double = 00.00

    fun build(): DogAppearance {
        return DogAppearance(name, age, color, length)
    }

}

class OwnerBuilder {

    var name: String = ""
    var country: String = ""

    fun build(): Owner {
        return Owner(name, country)
    }

}