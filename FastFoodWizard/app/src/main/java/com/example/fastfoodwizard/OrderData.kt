package com.example.fastfoodwizard

data class Order(
    var mainMeal: String? = null,
    val sideDishes: MutableList<String> = mutableListOf(),
    var drink: String? = null
) {
    fun isComplete(): Boolean =
        !mainMeal.isNullOrBlank() && sideDishes.isNotEmpty() && !drink.isNullOrBlank()
}

object OrderHolder {
    val current: Order = Order()

    fun reset() {
        current.mainMeal = null
        current.sideDishes.clear()
        current.drink = null
    }

    fun summary(): String {
        val main = current.mainMeal ?: "（未選）"
        val sides = if (current.sideDishes.isEmpty()) "（未選）" else current.sideDishes.joinToString(", ")
        val d = current.drink ?: "（未選）"
        return "主餐：$main\n副餐：$sides\n飲料：$d"
    }
}
