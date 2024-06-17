package by.ecomm.ecommcart.client

import java.util.UUID
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "product-service", url = "\${product-service.url}")
interface ProductServiceClient {
    @GetMapping("/api/product/{id}/exists")
    fun checkProductAvailable(
        @PathVariable id: UUID,
    ): ResponseEntity<Void>

}