package com.Rohit.Service;

import com.Rohit.Model.AuthToken;
import com.Rohit.Model.FoodItem;
import com.Rohit.Repo.IFoodRepo;
import com.Rohit.Repo.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    IFoodRepo iFoodRepo;

    @Autowired
    UAuthToken uAuthToken;

    public List<FoodItem> getFood() {
        return iFoodRepo.findAll();
    }

    public String addFood(String email, String tokenVal, FoodItem item) {
        if(uAuthToken.Authenticate(email,tokenVal)){
            iFoodRepo.save(item);
            return "FoodItem Added Successfully !!";
        }
        return "Un Authorized Access !!";
    }

    public String delFood(Integer id) {
        iFoodRepo.deleteById(id);
        return "1 FoodItem deleted !!";
    }
}
