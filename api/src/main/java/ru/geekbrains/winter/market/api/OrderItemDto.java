package ru.geekbrains.winter.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель заказа")
public class OrderItemDto {

    @Schema(description = "Номер заказанного товара", required = true, example = "1")
    private Long id;

    @Schema(description = "Наименование товара", required = true, example = "Тушенка")
    private String productTitle;

    @Schema(description = "Id заказа", required = true, example = "15")
    private Long orderId;

    @Schema(description = "Количество товара", required = true, example = "2")
    private int quantity;

    @Schema(description = "Стоимость одной позиции товара", required = true, example = "80.8")
    private BigDecimal pricePerProduct;

    @Schema(description = "Суммарная стоимость", required = true, example = "161.6")
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
