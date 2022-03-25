package ru.geekbrains.winter.market.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.core.entities.Category;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.repositories.ProductRepository;
import ru.geekbrains.winter.market.core.services.CategoryService;
import ru.geekbrains.winter.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void createNewProductTest(){
        ProductDto pd = new ProductDto();
        pd.setPrice(BigDecimal.valueOf(1000));
        pd.setCategoryTitle("Ball");
        pd.setTitle("Rubber ball");
        pd.setId(1L);

        Category category = new Category();
        category.setId(5L);
        category.setTitle("Ball");

        Product product = new Product();
        product.setPrice(pd.getPrice());
        product.setTitle(pd.getTitle());
        product.setCategory(category);
        product.setId(pd.getId());

        Mockito.doReturn(Optional.of(category)).when(categoryService).findByTitle("Ball");
        Mockito.doReturn(Optional.of(Product.class)).when(productRepository).save(product);

        Assertions.assertEquals(product.getTitle(), productService.createNewProduct(pd).getTitle());
        Assertions.assertEquals(product.getCategory().getTitle(), productService.createNewProduct(pd).getCategory().getTitle());
        Assertions.assertEquals(product.getPrice(), productService.createNewProduct(pd).getPrice());

    }
}
