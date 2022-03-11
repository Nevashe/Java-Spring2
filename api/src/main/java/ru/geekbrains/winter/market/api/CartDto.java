package ru.geekbrains.winter.market.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartDto {
    private List<CartItemDto> items;
    private BigDecimal totalPrice;

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
    }
}