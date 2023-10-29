package com.doctoranywhere.lnminh_daassignment.security.provider;

import com.doctoranywhere.lnminh_daassignment.entity.UserEntity;
import com.doctoranywhere.lnminh_daassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //Get username and password
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserEntity user = userRepository.findByUsername(username);
        if(user != null){
            //User exists in db then continue checking password
            if(passwordEncoder.matches(password,user.getPassword())){
                // Create authentication based on standard of Security
                List<GrantedAuthority> roles = new ArrayList<>();
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
                roles.add(authority);

                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(username,user.getPassword(),roles);

                return token;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Declare type of authentication
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
