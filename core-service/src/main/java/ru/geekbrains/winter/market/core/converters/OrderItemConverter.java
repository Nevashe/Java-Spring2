package ru.geekbrains.winter.market.core.converters;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.winter.market.api.OrderItemDto;
import ru.geekbrains.winter.market.core.entities.OrderItem;
import ru.geekbrains.winter.market.core.repositories.ProductRepository;
import ru.geekbrains.winter.market.core.services.OrderService;


@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    private final OrderService orderService;
    private final ProductRepository productRepository;
    public OrderItemDto entityToDto(OrderItem orderItem){
        OrderItemDto oid = new OrderItemDto();
        oid.setProductTitle(orderItem.getProduct().getTitle());

        oid.setQuantity(orderItem.getQuantity());
        oid.setPricePerProduct(orderItem.getPricePerProduct());
        oid.setPrice(orderItem.getPrice());
        return oid;
    }

    public OrderItem dtoToEntity(OrderItemDto orderItemDto){
        OrderItem oi = new OrderItem();
        oi.setPricePerProduct(orderItemDto.getPricePerProduct());
        oi.setQuantity(orderItemDto.getQuantity());
        oi.setPrice(orderItemDto.getPrice());
        oi.setProduct(productRepository.findByTitle(orderItemDto.getProductTitle()).get());
        return oi;
    }
}
