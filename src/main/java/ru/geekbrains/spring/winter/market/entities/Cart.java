package ru.geekbrains.spring.winter.market.entities;



import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Cart {
    private static Cart cart;

    private List<Product> productsInCart;

    private Cart(){
        productsInCart = new ArrayList<>();
    }
    public static Cart getCart(){ // #3
        if(cart == null){
            cart = new Cart();
        }
        return cart;
    }
}
