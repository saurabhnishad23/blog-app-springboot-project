package com.sunny.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String password;


//    public AuthRequest() {
//    }
//
//    public AuthRequest(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

}
