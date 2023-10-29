package com.doctoranywhere.lnminh_daassignment.service.impl;

import com.doctoranywhere.lnminh_daassignment.entity.UserEntity;
import com.doctoranywhere.lnminh_daassignment.payload.request.SignUpRequest;
import com.doctoranywhere.lnminh_daassignment.repository.UserRepository;
import com.doctoranywhere.lnminh_daassignment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public boolean insertUser(SignUpRequest signUpRequest) {
        boolean isSuccess = false;
        UserEntity user = new UserEntity();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(signUpRequest.getRole());

        try{
            userRepository.save(user);
            isSuccess = true;
        }catch (Exception e){
            System.out.println("Error insert user: " + e.getLocalizedMessage());
        }

        return isSuccess;
    }
}
