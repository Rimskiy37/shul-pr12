package tasks

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class Task1Test {
    private val cart = ShoppingCart()

    @Test
    fun `single item total is calculated correctly`() {
        cart.addItem(Item("Book", 15.0, 2))
        assertThat(cart.total()).isEqualTo(30.0)
    }

    @Test
    fun `added item is appended to end of items list`() {
        cart.addItem(Item("Apple", 1.0, 1))
        cart.addItem(Item("Banana", 2.0, 1))
        assertThat(cart.getItems().last().name).isEqualTo("Banana")
    }

    @Test
    fun `multiple items with different quantities calculate total correctly`() {
        cart.addItem(Item("Pen", 5.0, 3))
        cart.addItem(Item("Notebook", 10.0, 2))
        assertThat(cart.total()).isEqualTo(35.0)
    }

    @Test
    fun `promo SAVE10 applies 10 percent discount`() {
        cart.addItem(Item("Shirt", 100.0, 1))
        cart.applyPromo("SAVE10")
        assertThat(cart.total()).isEqualTo(90.0)
    }

    @Test
    fun `applying second promo throws exception`() {
        cart.applyPromo("SAVE10")
        assertThatThrownBy { cart.applyPromo("HALFOFF") }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Promo already applied")
    }

    @Test
    fun `item with negative price throws exception`() {
        assertThatThrownBy { cart.addItem(Item("Bad", -5.0, 1)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Price must be positive")
    }

    @Test
    fun `empty cart returns total zero`() {
        assertThat(cart.total()).isEqualTo(0.0)
    }
}