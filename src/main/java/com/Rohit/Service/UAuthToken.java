package com.Rohit.Service;

import com.Rohit.Model.AuthToken;
import com.Rohit.Repo.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UAuthToken {
    @Autowired
    ITokenRepo iTokenRepo;

    public boolean Authenticate(String email, String tokenVal) {
        AuthToken token =iTokenRepo.findFirstByTokValue(tokenVal);
        if(token==null){
            return false;
        }
        String existMail=token.getAdmin().getAdminEmail();
        return existMail.equals(email);
    }

    public boolean AuthenticateU(String email, String tokenVal) {
        AuthToken token =iTokenRepo.findFirstByTokValue(tokenVal);
        if(token==null){
            return false;
        }
        String existMail=token.getUser().getUserEmail();
        return existMail.equals(email);
    }
}
