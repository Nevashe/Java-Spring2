package ru.geekbrains.winter.market.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.winter.market.carts.model.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Map<String,Cart> mapTempCart;

    @PostConstruct
    public void init() {
        mapTempCart = new HashMap<>();
        mapTempCart.put("", new Cart());
    }

    public Cart getCurrentCart(String username) {
        checkCart(username);
        return mapTempCart.get(username);
    }

    public void add(Long productId, String username) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        Cart c = mapTempCart.get(username);
        c.add(product);
        mapTempCart.replace(username, c);
    }

    public void remove(Long productId, String username) {
        Cart c = mapTempCart.get(username);
        c.remove(productId);
        mapTempCart.replace(username, c);
    }

    public void clear(String username) {
        Cart c = mapTempCart.get(username);
        c.clear();
        mapTempCart.replace(username, c);
    }

    public void checkCart(String username){
        if(!mapTempCart.containsKey(username)){
            mapTempCart.put(username, new Cart());
        }
    }


}
