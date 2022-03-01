package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.winter.market.model.OrderModel;
import ru.geekbrains.spring.winter.market.entities.Order;
import ru.geekbrains.spring.winter.market.entities.OrderItem;
import ru.geekbrains.spring.winter.market.entities.User;
import ru.geekbrains.spring.winter.market.model.CartItem;
import ru.geekbrains.spring.winter.market.repositories.OrderItemRepository;
import ru.geekbrains.spring.winter.market.repositories.OrderRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;

    @Transactional
    public void createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new ResourceNotFoundException("Пользователь: " + principal.getName()+ "  не найден"));

        Order order = new Order(user,cartService.getCurrentCart().getTotalPrice());
        orderRepository.save(order);

        List<OrderItem> orderItemList= new ArrayList<>();
        List<CartItem> tmpListCartItems = cartService.getCurrentCart().getItems();
        for (CartItem tmpListCartItem : tmpListCartItems) {
            orderItemList.add(createOrderItems(tmpListCartItem, order));
        }
        orderItemRepository.saveAll(orderItemList);
    }

    public OrderItem createOrderItems(CartItem cartItem, Order order){
        return new OrderItem(
                productService.findById(cartItem.getProductId()).get(), // в корзине не могут лежать id не существующих товаров
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice()
                );
        // Возможно есть смысл перенести логику преобразования cartItem в orderItem куда-то в другое место
    }
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
}
