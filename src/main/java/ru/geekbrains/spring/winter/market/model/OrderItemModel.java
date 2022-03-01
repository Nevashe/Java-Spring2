package ru.geekbrains.spring.winter.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.winter.market.entities.OrderItem;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {

    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemModel(OrderItem orderItem) {
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }
}
