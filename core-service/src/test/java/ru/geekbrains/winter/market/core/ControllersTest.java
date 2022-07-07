package ru.geekbrains.winter.market.core;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.winter.market.core.entities.Category;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllersTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void findProductByIdTest() throws Exception {
        Category category = new Category();
        category.setId(5L);
        category.setTitle("Ball");

        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(1000));
        product.setTitle("Rubber ball");
        product.setCategory(category);
        product.setId(1L);

        Mockito.doReturn(Optional.of(product)).when(productService).findById(product.getId());

        mvc
                .perform(get("/api/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(product.getTitle()))
                .andExpect(jsonPath("$.categoryTitle").value(category.getTitle()));
    }
}
