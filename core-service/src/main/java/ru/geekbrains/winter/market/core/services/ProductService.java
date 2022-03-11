package ru.geekbrains.winter.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.core.entities.Category;
import ru.geekbrains.winter.market.core.entities.Filters;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.repositories.ProductRepository;
import ru.geekbrains.winter.market.core.repositories.specifications.ProductSpecs;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    public List<Product> getProductsWithPagingAndFiltering(Filters filters) {
        Specification<Product> spec = Specification.where(null);
        StringBuilder f = new StringBuilder();
        if (filters.getWord() != null) {
            spec = spec.and(ProductSpecs.titleContains(filters.getWord()));
        }
        if (filters.getMin() != null) {
            spec = spec.and(ProductSpecs.priceGreaterThanOrEq(filters.getMin()));
        }
        if (filters.getMax() != null) {
            spec = spec.and(ProductSpecs.priceLesserThanOrEq(filters.getMax()));
        }
        return productRepository.findAll(spec);
    }
}
