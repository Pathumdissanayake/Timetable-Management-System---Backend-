package com.example.TMS.Dto;

import com.example.TMS.Entity.Role;
import com.example.TMS.Entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String email;
    private Role role;
    private String password;
    private Users user;

    private String studentId;
    private String name;
    private String dob;
    private String nic;
    private String academicYr;
    private String address;
}
