package com.doctoranywhere.lnminh_daassignment.security.filter;

import com.doctoranywhere.lnminh_daassignment.security.utils.JwtHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// This Filter use to get token and decrypt token
@Service
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get value of header with key is Authorization
        String authen = request.getHeader("Authorization");
        if(authen != null && authen.startsWith("Bearer ")){
            // Substring from variable authen to get token
            String token = authen.substring(7);
            if(!token.isEmpty()){
                //Decrypt token
                try {
                    // Get list role are stored in token
                    String data = jwtHelper.validationToken(token);
                    //parser role to List
                    Type listType = new TypeToken<ArrayList<SimpleGrantedAuthority>>(){}.getType();
                    List<SimpleGrantedAuthority> roles = gson.fromJson(data,listType);


                    UsernamePasswordAuthenticationToken tokenAuthen =
                            new UsernamePasswordAuthenticationToken("","",roles);

                    SecurityContext context = SecurityContextHolder.getContext();
                    context.setAuthentication(tokenAuthen);

                }catch (Exception e){
                    System.out.println("Error encrypt token " + e.getLocalizedMessage());
                }


            }

        }

        filterChain.doFilter(request,response);
    }
}