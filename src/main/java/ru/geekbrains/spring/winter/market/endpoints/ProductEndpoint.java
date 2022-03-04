package ru.geekbrains.spring.winter.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.spring.winter.market.services.ProductService;
import ru.geekbrains.spring.winter.market.soap.products.GetAllProductsRequest;
import ru.geekbrains.spring.winter.market.soap.products.GetAllProductsResponse;
import ru.geekbrains.spring.winter.market.soap.products.GetProductByTitleRequest;
import ru.geekbrains.spring.winter.market.soap.products.GetProductByTitleResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/spring/winter/market/ws/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByTitleRequest")
    @ResponsePayload
    public GetProductByTitleResponse getProductByTitle(@RequestPayload GetProductByTitleRequest request) {
        GetProductByTitleResponse response = new GetProductByTitleResponse();
        response.setProduct(productService.getByTitleSoap(request.getTitle()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProductsSoap().forEach(response.getProducts()::add);
        return response;
    }
//
//    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.geekbrains.ru/spring/winter/market/ws/products">
//            <soapenv:Header/>
//            <soapenv:Body>
//                <f:getAllProductsRequest/>
//            </soapenv:Body>
//        </soapenv:Envelope>
    // Через postman все работает, не понимаю как отправить запрос при переходе по ссылке
//     плюс к этому, как вытащить данные на страницу пока не нашел
}

