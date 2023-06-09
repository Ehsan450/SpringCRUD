package io.github.hossensyedriadh.springcrud.service;

import io.github.hossensyedriadh.springcrud.entity.Product;
import io.github.hossensyedriadh.springcrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> products() {
        return this.productRepository.findAll(Sort.by(Sort.Order.by("sku")));
    }

    @Override
    public Optional<Product> product(String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        if (this.productRepository.findById(id).isPresent()) {
            this.productRepository.deleteById(id);
        }
    }
}
