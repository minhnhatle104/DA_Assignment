package com.doctoranywhere.lnminh_daassignment.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignInRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
