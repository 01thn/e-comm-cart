package by.ecomm.ecommcart.dto.mapper

import by.ecomm.ecommcart.dto.CartDto
import by.ecomm.ecommcart.entity.Cart
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CartMapper {

    fun toDto(entity: Cart): CartDto

}
