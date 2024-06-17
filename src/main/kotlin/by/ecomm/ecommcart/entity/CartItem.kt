package by.ecomm.ecommcart.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.cache.spi.support.AbstractReadWriteAccess

@Entity
data class CartItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne @JoinColumn(name = "cart_id")
    val cart: Cart,
    val productId: UUID,
    val createdAt: LocalDateTime = LocalDateTime.now()
)