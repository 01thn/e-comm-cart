package by.ecomm.ecommcart.controller

import by.ecomm.ecommcart.dto.CartDto
import by.ecomm.ecommcart.service.CartService
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cart")
class CartController(private val cartService: CartService) {

    @GetMapping("/{userId}")
    fun getCart(@PathVariable userId: UUID): ResponseEntity<CartDto> {
        return ResponseEntity(cartService.getCartByUserId(userId), HttpStatus.OK)
    }

    @PostMapping("/{userId}/items")
    fun addItemToCart(
        @PathVariable userId: UUID,
        @RequestParam productId: UUID
    ): ResponseEntity<Void> {
        cartService.addItemToCart(userId, productId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{userId}")
    fun clearCart(@PathVariable userId: UUID): ResponseEntity<Void> {
        cartService.clearCart(userId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{userId}/items")
    fun deleteItemFromCart(
        @PathVariable userId: UUID,
        @RequestParam productId: UUID
    ): ResponseEntity<Void> {
        cartService.deleteItemFromCart(userId, productId)
        return ResponseEntity.ok().build()
    }

}