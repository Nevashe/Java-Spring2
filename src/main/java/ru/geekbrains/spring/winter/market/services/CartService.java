package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.entities.Cart;
import ru.geekbrains.spring.winter.market.entities.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;

    public List<Product> getCart() {
        return Cart.getCart().getProductsInCart();
    }

    public void addProductInCart(Long productId){
        Product product = productService.findById(productId).get();
        Cart.getCart().getProductsInCart().add(product);
    }
    public void removeProductInCart(Long productId){
        Product product = productService.findById(productId).get();
        for (int i = 0; i < Cart.getCart().getProductsInCart().size(); i++) {
            if(Cart.getCart().getProductsInCart().get(i).equals(product))
            Cart.getCart().getProductsInCart().remove(i);
            return;
            }
        }
}
