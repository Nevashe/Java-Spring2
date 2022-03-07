package ru.geekbrains.winter.market.front.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class MainController {

    @GetMapping()
    public void showHomePage() {
    }

}
