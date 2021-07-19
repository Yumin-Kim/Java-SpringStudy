package springcore.fastcamp.fastcamp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springcore.fastcamp.fastcamp.domin.Product;
import springcore.fastcamp.fastcamp.dto.ProductMyPriceRequestDto;
import springcore.fastcamp.fastcamp.dto.ProductRequestDto;
import springcore.fastcamp.fastcamp.security.UserDetailsImpl;
import springcore.fastcamp.fastcamp.service.ProductService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<Product> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) throws SQLException {
        Long userId = userDetails.getUser().getId();
        return productService.getSelectedProducts(userId);
//        return productService.getProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody ProductRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails)  {
        Long id = userDetails.getUser().getId();
        System.out.println("id = " + id);
        Product product = productService.createProduct(requestDto, id);
        return product;
    }

    @PutMapping("/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto myPriceRequestDto) throws SQLException {
        Product product = productService.updateProduct(id, myPriceRequestDto);
        return product.getId();
    }
}
