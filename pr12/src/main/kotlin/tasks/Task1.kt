package tasks

data class Item(val name: String, val price: Double, val quantity: Int)

class ShoppingCart {
    private val items = mutableListOf<Item>()
    private var promoCode: String? = null

    fun addItem(item: Item) {
        require(item.price > 0) { "Price must be positive" }
        items.add(item)
    }

    fun getItems(): List<Item> = items

    fun applyPromo(code: String) {
        check(promoCode == null) { "Promo already applied" }
        promoCode = code
    }

    fun total(): Double {
        val subtotal = items.sumOf { it.price * it.quantity }
        return applyDiscount(subtotal)
    }

    private fun applyDiscount(amount: Double): Double = when (promoCode) {
        "SAVE10" -> amount * 0.9
        "HALFOFF" -> amount * 0.5
        else -> amount
    }
}