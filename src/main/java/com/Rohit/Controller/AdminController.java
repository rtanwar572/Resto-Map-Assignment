package com.Rohit.Controller;

import com.Rohit.Model.Admin;
import com.Rohit.Model.FoodItem;
import com.Rohit.Model.User;
import com.Rohit.Model.dto.SignInInputDto;
import com.Rohit.Service.AdminService;
import com.Rohit.Service.FoodService;
import com.Rohit.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;
    @Autowired
    FoodService foodService;
    @PostMapping("/signUpAdmin")
    String signUpAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }
    @PostMapping("/SignInAdmin")
    String signInAdmin(@RequestBody SignInInputDto signInInputDto){
        return adminService.signInAdmin(signInInputDto);
    }

    @PostMapping("food/adminEmail/{email}/tokenVal/{tokenVal}")
    public String addFood(@PathVariable String email,@PathVariable String tokenVal, @RequestBody FoodItem item){
        return foodService.addFood(email,tokenVal,item);
    }
    @GetMapping("/Users")
    public List<User> getUser() {
        return userService.getUser();
    }
    @GetMapping("/foods")
    public List<FoodItem> getFood(){
        return foodService.getFood();
    }

    @DeleteMapping("/foodItem/id/{}")
    public String DelFood(@PathVariable Integer id){
        return foodService.delFood(id);
    }

}
