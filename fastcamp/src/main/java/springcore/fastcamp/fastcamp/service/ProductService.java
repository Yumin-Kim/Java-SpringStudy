package springcore.fastcamp.fastcamp.service;

import springcore.fastcamp.fastcamp.domin.Product;
import springcore.fastcamp.fastcamp.dto.ProductMyPriceRequestDto;
import springcore.fastcamp.fastcamp.dto.ProductRequestDto;
import springcore.fastcamp.fastcamp.repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    public List<Product> getProducts() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        return productRepository.getProducts();
    }

    public Product createProduct(ProductRequestDto requestDto) throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        Product product = new Product(requestDto);
        productRepository.createProduct(product);
        return product;
    }

    public Product updateProduct(Long id, ProductMyPriceRequestDto myPriceRequestDto) throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.getProduct(id);
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다");
        }
        int myprice = myPriceRequestDto.getMyprice();
        productRepository.updateProductMyPrice(id, myprice);
        return product;
    }
}
