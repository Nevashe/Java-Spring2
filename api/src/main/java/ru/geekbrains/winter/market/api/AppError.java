package ru.geekbrains.winter.market.api;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Модель ошибки")
public class AppError {

    @Schema(description = "Статус код ошибки", required = true, example = "404")
    private int statusCode;

    @Schema(description = "Описание ошибки", required = true, example = "Ничего не найдено")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppError() {
    }

    public AppError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
