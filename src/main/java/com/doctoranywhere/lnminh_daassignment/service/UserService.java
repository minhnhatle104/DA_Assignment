package com.doctoranywhere.lnminh_daassignment.service;

import com.doctoranywhere.lnminh_daassignment.payload.request.SignUpRequest;

public interface UserService {
    boolean insertUser(SignUpRequest signUpRequest);
}
