package com.Rohit.Controller;

import com.Rohit.Model.Order;
import com.Rohit.Model.User;
import com.Rohit.Model.dto.SignInInputDto;
import com.Rohit.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signUpUser")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/SignInUser")
    public String signInUser(@RequestBody SignInInputDto signInInputDto){
        return userService.signInUser(signInInputDto);
    }
    @PostMapping("/userEmail/{email}/tokenVal/{tokenVal}/Orders")
    public String placeOrder(@PathVariable String email,@PathVariable String tokenVal, @RequestBody Order order){
        return userService.placeOrder(email,tokenVal,order);
    }
    @GetMapping("/orderById/{id}")
    public Order getOrder(@PathVariable Integer id){
        return userService.getOrderById(id);
    }

}
