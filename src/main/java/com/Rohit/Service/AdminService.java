package com.Rohit.Service;

import com.Rohit.Model.Admin;
import com.Rohit.Model.AuthToken;
import com.Rohit.Model.dto.SignInInputDto;
import com.Rohit.Repo.IAdminRepo;
import com.Rohit.Repo.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    IAdminRepo iAdminRepo;
    @Autowired
    ITokenRepo iTokenRepo;

    public String addAdmin(Admin admin) {
        String email=admin.getAdminEmail();
        Admin existadmin=iAdminRepo.findFirstByAdminEmail(email);
        if(existadmin!=null){
            return "admin alReady Exist just Login";
        }
        String pass=admin.getAdminpass();
        try {
            String encryptedpass=PassEncryptor.encrypt(pass);
            admin.setAdminpass(encryptedpass);
            iAdminRepo.save(admin);
            return "admin Registered !!";
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String signInAdmin(SignInInputDto signInInputDto) {
        String email=signInInputDto.getEmail();
        Admin existadmin=iAdminRepo.findFirstByAdminEmail(email);
        if(existadmin==null){
            return "Invalid id and pass, You May SignUp First";
        }
        String pass=signInInputDto.getPass();
        try {
            String encryptPass=PassEncryptor.encrypt(pass);
            if(encryptPass.equals(existadmin.getAdminpass())){
                AuthToken token=new AuthToken(existadmin);
                iTokenRepo.save(token);
                return token.getTokValue();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "Invalid Credentials !";

    }
    //sols
    //admin check

}
