package ru.geekbrains.winter.market.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.market.api.OrderDto;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.core.converters.OrderConverter;
import ru.geekbrains.winter.market.core.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @Operation(
            summary = "Запрос на оформление нового заказа",
            responses = {
                    @ApiResponse(
                            description = "Заказ успешно оформлен", responseCode = "201",
                            content = @Content(schema = @Schema())
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username /*, @RequestBody OrderData orderData */) {
        orderService.createOrder(username);
    }

    @Operation(
            summary = "Запрос получение списка заказов",
            responses = {
                    @ApiResponse(
                            description = "Заказ успешно оформлен", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping                             //TODO: думаю есть смысл переделать List на PageDto, заказов может быть многo
    public List<OrderDto> getUserOrders(@RequestHeader @Parameter(description = "Имя пользователя", required = true) String username) {
        return orderService.findByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
