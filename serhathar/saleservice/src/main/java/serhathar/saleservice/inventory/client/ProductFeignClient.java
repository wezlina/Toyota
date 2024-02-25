package serhathar.saleservice.inventory.client;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import serhathar.saleservice.inventory.api.ProductDto;
import serhathar.saleservice.inventory.web.ProductRequest;
import serhathar.saleservice.inventory.web.ProductResponse;

import java.util.List;

@FeignClient(name = "product-service", path = "/v1/product-service")
@EnableFeignClients
public interface ProductFeignClient {
    @GetMapping(path = "/get/{categoryName}")
    ResponseEntity<List<ProductResponse>> getAllProducts(@PathVariable String categoryName);

    @GetMapping(path = "/get/{id}")
    ResponseEntity<List<ProductResponse>> getProductById(@PathVariable String id);

    @GetMapping(path = "getBy/{id}")
    ProductDto getProductById1(@PathVariable String id);

    @GetMapping(path = "/get-all")
    ResponseEntity<List<ProductResponse>> getAllProducts();

    @GetMapping(path = "/get-active")
    ResponseEntity<List<ProductResponse>> getActiveProducts();

    @GetMapping(path = "/get-all-active")
    ResponseEntity<List<ProductResponse>> getAllActiveProducts();


    @PutMapping(path = "/activate-status/{id}")
    String activateCategory(@PathVariable(value = "id") String id);

    @PutMapping(path = "/update/{id}")
    ResponseEntity<ProductResponse> updateProduct(@PathVariable(value = "id") String id, @Valid @RequestBody ProductRequest request);

    @PutMapping(path = "/delete-status/{id}")
    String delete_statusProduct(@PathVariable(value = "id") String id);
}
