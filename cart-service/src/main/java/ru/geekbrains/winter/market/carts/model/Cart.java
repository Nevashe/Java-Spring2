package ru.geekbrains.winter.market.carts.model;

import lombok.Data;
import ru.geekbrains.winter.market.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public boolean add(Long productId) {
        for (CartItem item : items) {
            if (productId.equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }
    public void addNewProduct(ProductDto product) {
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void remove(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    public void merge(Cart tempCart) {
            for(CartItem tempItem: tempCart.getItems()){
                boolean tmp = true; // без флага не придумал как
                for(CartItem item: items){
                    if (tempItem.getProductId().equals(item.getProductId())) {
                        item.changeQuantity(tempItem.getQuantity());
                        tmp = false;
                        break;
                    }
                }
            if (tmp) {
               items.add(tempItem);
            }
        }
        recalculate();
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice());
        }
    }
}
