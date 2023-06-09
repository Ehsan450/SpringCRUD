package io.github.hossensyedriadh.springcrud.service;

import io.github.hossensyedriadh.springcrud.entity.Product;

import java.util.List;
import java.util.Optional;

public sealed interface ProductService permits ProductServiceImpl {
    List<Product> products();

    Optional<Product> product(String id);

    Product save(Product product);

    void delete(String id);
}
