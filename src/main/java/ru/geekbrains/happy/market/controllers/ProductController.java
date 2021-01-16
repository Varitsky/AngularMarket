package ru.geekbrains.happy.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.happy.market.dto.ProductDto;
import ru.geekbrains.happy.market.model.Product;
import ru.geekbrains.happy.market.repositories.ProductRepository;
import ru.geekbrains.happy.market.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
//    public List<Product> findAllProducts( // продукты
//    public Page<Product> findAllProducts( // страницы
      public Page<ProductDto> findAllProducts(
            @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page =1 ;
        }
//        return productService.findAll(page).getContent();
        return productService.findAll(page);
    }





    /** предыдущая версия*/
//    @GetMapping
//    public List<Product> findAllProducts(
//            @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
//            @RequestParam(name = "max_price", required = false) Integer maxPrice
//    ) {
//        if (maxPrice == null) {
//            maxPrice = Integer.MAX_VALUE;
//        }
//        return productService.findAllByPrice(minPrice, maxPrice);
//    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findProductById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.saveOrUpdate(product);
    }


}
