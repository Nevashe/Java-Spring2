package ru.geekbrains.winter.market.core.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.market.api.AppError;
import ru.geekbrains.winter.market.api.StringResponse;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping
    public ResponseEntity adminPanel(@RequestHeader String[] roles) {
        //Наверно есть смысл enum сделать под роли, или можно как-то иначе?
        String roleAdmin = "ROLE_ADMIN";
        for (String role : roles){
            if (role.equals(roleAdmin)){
                return new ResponseEntity<>(new StringResponse("You Admin"),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(), "Тебе сюда нельзя"), HttpStatus.FORBIDDEN);
    }
}
