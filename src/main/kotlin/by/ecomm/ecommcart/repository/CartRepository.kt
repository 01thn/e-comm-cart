package by.ecomm.ecommcart.repository

import by.ecomm.ecommcart.entity.Cart
import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : CrudRepository<Cart, Long> {
    fun findByUserId(userId: UUID): Cart?
}