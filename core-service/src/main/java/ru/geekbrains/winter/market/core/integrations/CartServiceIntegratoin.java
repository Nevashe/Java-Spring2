package ru.geekbrains.winter.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.winter.market.api.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegratoin {
    private final RestTemplate restTemplate;
    @Value("${portCart}")
    private String portCart;
    @Value("${server.url}")
    private String url;

    public CartDto getCurrentCart() {
        System.out.println(portCart);
        return restTemplate.getForObject(url+portCart+"/winter-carts/api/v1/cart/", CartDto.class);
    }

    public Void clear(){
        return restTemplate.getForObject(url+portCart+"/winter-carts/api/v1/cart/clear", Void.class);
    }
}
