package ru.geekbrains.winter.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.winter.market.api.CartDto;
import ru.geekbrains.winter.market.api.CartItemDto;
import ru.geekbrains.winter.market.core.entities.Order;
import ru.geekbrains.winter.market.core.entities.OrderItem;
import ru.geekbrains.winter.market.core.entities.User;
import ru.geekbrains.winter.market.core.integrations.CartServiceIntegratoin;
import ru.geekbrains.winter.market.core.repositories.OrderItemRepository;
import ru.geekbrains.winter.market.core.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final CartServiceIntegratoin cartServiceIntegration;

    @Transactional
    public void createOrder(User user) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart();

        Order order = new Order(user, cartDto.getTotalPrice());
        orderRepository.save(order);

        List<OrderItem> orderItemList= new ArrayList<>();
        for (CartItemDto tmpListCartItem : cartDto.getItems()) {
            orderItemList.add(createOrderItems(tmpListCartItem, order));
        }
        orderItemRepository.saveAll(orderItemList);
        cartServiceIntegration.clear();
    }

    public OrderItem createOrderItems(CartItemDto cartItem, Order order){
        return new OrderItem(
                productService.findById(cartItem.getProductId()).get(), // в корзине не могут лежать id не существующих товаров
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice()
        );
    }
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
}
