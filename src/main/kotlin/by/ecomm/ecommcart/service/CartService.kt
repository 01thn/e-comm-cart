package by.ecomm.ecommcart.service

import by.ecomm.ecommcart.client.ProductServiceClient
import by.ecomm.ecommcart.dto.CartDto
import by.ecomm.ecommcart.entity.Cart
import by.ecomm.ecommcart.entity.CartItem
import by.ecomm.ecommcart.exception.CartServiceException
import by.ecomm.ecommcart.repository.CartRepository
import by.ecomm.ecommcart.dto.mapper.CartMapper
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CartService(
    private val cartRepository: CartRepository,
    private val serviceClient: ProductServiceClient,
    private val cartMapper: CartMapper
) {

    fun getCartByUserId(userId: UUID): CartDto? {
        return cartRepository.findByUserId(userId)?.let { cartMapper.toDto(it) }
            ?: throw CartServiceException("There is no cart with id $userId")
    }

    fun clearCart(userId: UUID) {
        val cart = (cartRepository.findByUserId(userId) ?: return).also {
            it.items.clear()
        }
        cartRepository.save(cart)
    }

    fun addItemToCart(userId: UUID, productId: UUID): CartDto {
        if (serviceClient.checkProductAvailable(
                productId
            ).statusCode != HttpStatus.OK
        ) {
            throw CartServiceException("Product with ID $productId does not exist.")
        }

        val cart = cartRepository.findByUserId(userId) ?: Cart(userId = userId)
        cart.items.add(
            CartItem(
                cart = cart,
                productId = productId
            )
        )

        cartRepository.save(cart)
        return cartMapper.toDto(cart)
    }

    fun deleteItemFromCart(userId: UUID, productId: UUID) {
        val cart = cartRepository.findByUserId(userId)
        if (cart != null) {
            val find = cart.items.find { it.productId == productId }
            cart.items.remove(find)
            cartRepository.save(cart)
        }
    }

}