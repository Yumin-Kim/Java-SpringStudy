package springcore.fastcamp.fastcamp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springcore.fastcamp.fastcamp.domin.Product;
import springcore.fastcamp.fastcamp.dto.ProductMyPriceRequestDto;
import springcore.fastcamp.fastcamp.dto.ProductRequestDto;
import springcore.fastcamp.fastcamp.service.ProductService;

import javax.print.PrintService;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestControllerAdvice
@RequestMapping(value = "/api/products")
public class ProductController {
    @GetMapping()
    public List<Product> getProducts() throws SQLException{
        ProductService productService = new ProductService();
        return productService.getProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
        ProductService productService = new ProductService();
        return productService.createProduct(requestDto);
    }

    @PutMapping
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto myPriceRequestDto) throws SQLException {
        ProductService productService = new ProductService();
        Product product = productService.updateProduct(id, myPriceRequestDto);
        return product.getId();
    }
}
