package ru.geekbrains.winter.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Модель страницы")
public class PageDto<E> {

    @Schema(description = "Список текущей страницы")
    private List<E> items;

    @Schema(description = "Номер текущей страницы", required = true, example = "3")
    private int page;

    @Schema(description = "Список страниц", required = true, example = "5")
    private int totalPages;

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public PageDto() {
    }
}
