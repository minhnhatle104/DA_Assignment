package com.doctoranywhere.lnminh_daassignment.controller;

import com.doctoranywhere.lnminh_daassignment.payload.request.SignInRequest;
import com.doctoranywhere.lnminh_daassignment.payload.request.SignUpRequest;
import com.doctoranywhere.lnminh_daassignment.payload.response.BaseResponse;
import com.doctoranywhere.lnminh_daassignment.security.utils.JwtHelper;
import com.doctoranywhere.lnminh_daassignment.service.UserService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtHelper jwtHelper;
    private Gson gson = new Gson();

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse> signin(@RequestBody @Valid SignInRequest signInRequest){
        UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authen);

        List<GrantedAuthority> listRoles = (List<GrantedAuthority>) authentication.getAuthorities();
        String dataToken = gson.toJson(listRoles);

        String token = jwtHelper.generateToken(dataToken);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setMessage("Sign in successfully");
        baseResponse.setData(token);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signup(@RequestBody SignUpRequest signUpRequest){
        boolean isSuccess = userService.insertUser(signUpRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(isSuccess ? 200 : 400);
        baseResponse.setMessage(isSuccess ? "Sign up successfully" : "Sign up failed");
        baseResponse.setData(isSuccess);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
