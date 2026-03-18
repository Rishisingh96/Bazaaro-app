package com.rishi.controller;

import com.rishi.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public ApiResponse HomeControllerHandler() {
        ApiResponse apiResponse = new ApiResponse();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        apiResponse.setMessage("Hello Welcome to ecommerce multi vendor");
        System.out.println("Chal raha hai--------------------------------");
        return apiResponse;
    }

    //Without API
    /*   @GetMapping
    public String HomeControllerHandler(){

        return "Welcome to ecommerce Website";
    }*/

    //Create API
//    @GetMapping
//    public ApiResponse HomeControllerHandler(){
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Welcome to ecommerce Website");
//        return apiResponse;
//    }
}
