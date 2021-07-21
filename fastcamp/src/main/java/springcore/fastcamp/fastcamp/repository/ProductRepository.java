package springcore.fastcamp.fastcamp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springcore.fastcamp.fastcamp.domin.Product;
import springcore.fastcamp.fastcamp.domin.ProductUser;

import javax.persistence.EntityManager;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;
    public List<Product> getProducts() {
        List<Product> findAllProduct = em.createQuery(
                "select p from Product p  " +
                        "join fetch p.productUser pu ",
                Product.class).getResultList();
        return findAllProduct;
    }

    public List<Product> getUserSelectedProduct(Long id){
        List<Product> resultList = em.createQuery("select p from Product p " +
                "join fetch p.productUser pu " +
                "where pu.id =:userId", Product.class)
                .setParameter("userId",id)
                .getResultList();
        return resultList;
    }

    public void createProduct(Product product, ProductUser user) {
        LocalDateTime now = LocalDateTime.now();
        product
                .setProductUser(user)
                .setModifiedAt(now)
                .setCreatedAt(now);
        em.persist(product);
    }

    public Product getProduct(Long id) {
        return em.find(Product.class, id);
    }

    public void updateProductMyPrice(Long id, int myprice) {
        Product product = em.find(Product.class, id);
        product.setMyprice(myprice);
        product.setModifiedAt(LocalDateTime.now());
    }

    public Optional<Product> findById(Long productId) {
        Product product = em.find(Product.class, productId);
        return Optional.ofNullable(product);
    }
}

