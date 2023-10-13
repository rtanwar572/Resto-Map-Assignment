package com.Rohit.Repo;

import com.Rohit.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodRepo extends JpaRepository<FoodItem,Integer> {

}
