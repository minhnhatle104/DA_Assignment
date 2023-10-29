package com.doctoranywhere.lnminh_daassignment.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

    /**
     *  status code of response
     */
    private int status;

    /**
     *  message of response
     */
    private String message;

    /**
     *  data return to client
     */
    private Object data;

}
