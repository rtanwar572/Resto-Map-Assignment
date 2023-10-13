package com.Rohit.Repo;

import com.Rohit.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Integer> {
//    Admin findFirstByadminEmail(String email);

    Admin findFirstByAdminEmail(String email);
}
