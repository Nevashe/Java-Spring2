package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.entities.Category;
import ru.geekbrains.spring.winter.market.repositories.CategoryRepository;
import ru.geekbrains.spring.winter.market.soap.category.CategorySoap;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    // SOAP
    public static final Function<Category, CategorySoap> functionEntityToSoap = c -> {
        CategorySoap cs = new CategorySoap();
        cs.setTitle(c.getTitle());
        c.getProducts().stream().map(ProductService.functionEntityToSoap);
        return cs;
    };

    public CategorySoap getByTitleSoap(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }

}
