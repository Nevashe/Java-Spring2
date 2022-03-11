package ru.geekbrains.winter.market.carts.model;

import lombok.Data;
import ru.geekbrains.winter.market.api.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(ProductDto product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
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
        setTotalPrice(BigDecimal.valueOf(0));
    }

    private void recalculate() {
        totalPrice = BigDecimal.valueOf(0);
        for (CartItem item : items) {
            totalPrice.add(item.getPrice()).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
