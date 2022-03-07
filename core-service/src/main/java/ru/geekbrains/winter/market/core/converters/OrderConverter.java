package ru.geekbrains.winter.market.core.converters;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.winter.market.api.OrderDto;
import ru.geekbrains.winter.market.core.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order) {
        OrderDto od = new OrderDto();
        od.setId(order.getId());
        od.setUserId(order.getUser().getId());
        od.setItems(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        od.setTotalPrice(order.getTotalPrice());
        return od;

    }
}
