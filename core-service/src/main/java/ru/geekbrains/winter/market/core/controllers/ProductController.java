package ru.geekbrains.winter.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.core.converters.ProductConverter;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page<ProductDto> findProducts(
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(defaultValue = "1", name = "p") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        Specification<Product> spec = productService.createSpecByFilters(minPrice, maxPrice, title);
        return productService.findAll(spec, page - 1).map(productConverter::entityToDto);

    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
        return productConverter.entityToDto(p);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        Product p = productService.createNewProduct(productDto);
        return productConverter.entityToDto(p);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
