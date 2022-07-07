package ru.geekbrains.winter.market.core.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Filters {
    private String word;
    private Double max;
    private Double min;
}
