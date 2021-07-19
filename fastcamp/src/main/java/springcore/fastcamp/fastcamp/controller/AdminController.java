package springcore.fastcamp.fastcamp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springcore.fastcamp.fastcamp.domin.Product;
import springcore.fastcamp.fastcamp.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/api/admin/products")
    public List<Product> getAllProduct(){
        return productService.getProducts();
    }
}
