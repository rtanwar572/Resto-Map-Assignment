package com.Rohit.Service;

import com.Rohit.Model.AuthToken;
import com.Rohit.Model.Order;
import com.Rohit.Model.User;
import com.Rohit.Model.dto.SignInInputDto;
import com.Rohit.Repo.IOrderRepo;
import com.Rohit.Repo.ITokenRepo;
import com.Rohit.Repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    ITokenRepo iTokenRepo;

    @Autowired
    UAuthToken uAuthToken;
    @Autowired
    IOrderRepo iOrderRepo;
    public String addUser(User user) {
        String email=user.getUserEmail();
        User existuser=iUserRepo.findFirstByUserEmail(email);
        if(existuser!=null){
            return "User alReady Exist just Login";
        }
        String pass=user.getUserPass();
        try {
            String encryptedpass=PassEncryptor.encrypt(pass);
            user.setUserPass(encryptedpass);
            iUserRepo.save(user);
            return "User Registered !!";
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUser() {
        return iUserRepo.findAll();
    }

    public String signInUser(SignInInputDto signInInputDto) {
        String email=signInInputDto.getEmail();
        User existuser=iUserRepo.findFirstByUserEmail(email);
        if(existuser==null){
            return "Invalid id and pass, You May SignUp First";
        }
        String pass=signInInputDto.getPass();
        try {
            String encryptPass=PassEncryptor.encrypt(pass);
            if(encryptPass.equals(existuser.getUserPass())){
                AuthToken token=new AuthToken(existuser);
                iTokenRepo.save(token);
                return token.getTokValue();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "Invalid Credentials !";
    }

    public String placeOrder(String email, String tokenVal, Order order) {
        if(uAuthToken.AuthenticateU(email,tokenVal)){
            iOrderRepo.save(order);
            return "Order Placed Successfully !!";
        }
        return "Un-Authorized User";
    }

    public Order getOrderById(Integer id) {
      return  iOrderRepo.findById(id).orElseThrow();
    }
}
