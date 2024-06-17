package by.ecomm.ecommcart.repository

import by.ecomm.ecommcart.entity.CartItem
import org.springframework.data.repository.CrudRepository

interface CartItemRepository : CrudRepository<CartItem, Long>