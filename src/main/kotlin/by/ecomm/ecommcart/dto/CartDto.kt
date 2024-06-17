package by.ecomm.ecommcart.dto

import by.ecomm.ecommcart.entity.CartItem
import jakarta.persistence.CascadeType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import java.util.UUID

data class CartDto(
    val id: Long = 0,
    val userId: UUID,
    var items: ArrayList<CartItem> = ArrayList(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
)