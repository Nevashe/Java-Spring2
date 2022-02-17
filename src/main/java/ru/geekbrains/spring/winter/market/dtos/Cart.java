package ru.geekbrains.spring.winter.market.dtos;

import lombok.Data;
import ru.geekbrains.spring.winter.market.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) { // TODO: Доработано
        int tmpIdItems = getIdItemInCart(product.getId());
        if(tmpIdItems >= 0){
            int tmpQuantity = items.get(tmpIdItems).getQuantity();
            items.set(tmpIdItems, convertProductToCartItem(product, ++tmpQuantity));
        } else{
            items.add(convertProductToCartItem(product, 1));
        }

        recalculate();
    }

    public void subtract(Product product) {
        int tmpIdItems = getIdItemInCart(product.getId());
        int tmpQuantity = items.get(tmpIdItems).getQuantity();
        if(tmpQuantity > 1) {
            items.set(tmpIdItems, convertProductToCartItem(product, --tmpQuantity));
        }
        recalculate();
    }

    public void delete(Long productId) {
        int tmpIdItems = getIdItemInCart(productId);
        if(tmpIdItems>=0){
            items.remove(getIdItemInCart(productId));
            recalculate();
        }
    }

    public int getIdItemInCart(Long productId) { //
        for(CartItem item : items){
            if(item.getProductId() == productId){
                return items.indexOf(item);
            }
        }
        return -1;
    }

    public CartItem convertProductToCartItem(Product product, int quantity){
        return new CartItem(
                product.getId(),
                product.getTitle(),
                quantity,
                product.getPrice(),
                product.getPrice()*quantity);
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }
}
