package springcore.fastcamp.fastcamp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.Product;
import springcore.fastcamp.fastcamp.domin.User;
import springcore.fastcamp.fastcamp.dto.ProductMyPriceRequestDto;
import springcore.fastcamp.fastcamp.dto.ProductRequestDto;
import springcore.fastcamp.fastcamp.repository.ProductRepository;
import springcore.fastcamp.fastcamp.repository.ProductUserRepository;
import springcore.fastcamp.fastcamp.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    final private ProductRepository productRepository;
    final private ProductUserRepository userRepository;

    public List<Product> getSelectedProducts(Long id){
        return productRepository.getUserSelectedProduct(id);
    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Transactional(readOnly = false)
    public Product createProduct(ProductRequestDto requestDto, Long userId) {
        Product product = new Product(requestDto);
        userRepository.findById(userId)
                .ifPresent(findUser -> {
                    productRepository.createProduct(product , findUser);
                });


        return product;
    }

    @Transactional(readOnly = false)
    public Product updateProduct(Long id, ProductMyPriceRequestDto myPriceRequestDto) {
        Product product = productRepository.getProduct(id);
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다");
        }
        int myprice = myPriceRequestDto.getMyprice();
        productRepository.updateProductMyPrice(id, myprice);
        return product;
    }
}
