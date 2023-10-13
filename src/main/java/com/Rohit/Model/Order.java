package com.Rohit.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Order.class,property = "ordId")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordId;
    private Status ordStatus;

    //only authenicated user can order something....

    @OneToMany
    @JoinColumn(name = "order-fk")
    List<FoodItem> foodItemList;

    @OneToOne
    @JoinColumn(name = "user-fk")
    User user;

}
