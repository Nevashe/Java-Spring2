package ru.geekbrains.spring.winter.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.winter.market.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    private Long id;
    private Long userId;
    private List<OrderItemModel> items;
    private int totalPrice;

    public OrderModel(Order order) {
        this.id = order.getId();
        this.userId = order.getUser().getId();
        this.items = order.getItems().stream().map(OrderItemModel::new).collect(Collectors.toList());
        this.totalPrice = order.getTotalPrice();
    }


}
