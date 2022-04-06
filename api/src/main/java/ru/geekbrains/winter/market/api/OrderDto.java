package ru.geekbrains.winter.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Модель заказа")
public class OrderDto {

    @Schema(description = "Номер заказа", required = true, example = "15")
    private Long id;

    @Schema(description = "Имя пользователя", required = true, example = "Cameron")
    private String username;

    @Schema(description = "Список заказанных товаров", required = true)
    private List<OrderItemDto> items;

    @Schema(description = "Адрес пользователя", required = true, example = "cameron@gmail.com")
    private String address;

    @Schema(description = "Телефон пользователя", required = true, example = "88005553535", minLength = 11, maxLength = 11)
    private String phone;

    @Schema(description = "Стоимость заказа", required = true, example = "15355.00")
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
