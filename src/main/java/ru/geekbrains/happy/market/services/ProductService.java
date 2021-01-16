package ru.geekbrains.happy.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.happy.market.dto.ProductDto;
import ru.geekbrains.happy.market.model.Product;
import ru.geekbrains.happy.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

//    public List<Product> findAll() {
//        return productRepository.findAll();
//    } //Before DTO

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

//    public Page<Product> findAll(int page) {
//        return productRepository.findAll(PageRequest.of(page-1, 6));
//    }

    public Page<ProductDto> findAll(int page) {
        Page<Product> originalPage = productRepository.findAll(PageRequest.of(page-1, 6));
        return new PageImpl<>(originalPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), originalPage.getPageable(), originalPage.getTotalElements());
    }

    public List<Product> findAllByPrice(int min, int max) {
        return productRepository.findAllByPriceBetween(min, max);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
