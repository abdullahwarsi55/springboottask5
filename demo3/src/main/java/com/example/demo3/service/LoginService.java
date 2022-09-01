/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo3.service;

import com.example.demo3.commons.APIResponse;
import com.example.demo3.utils.JwtUtils;
import com.example.demo3.dto.LoginRequestDTO;
import com.example.demo3.dto.SignUpRequestDTO;
import com.example.demo3.entity.User;
import com.example.demo3.repo.UserRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
        APIResponse apiResponse = new APIResponse();

     

    
        User userEntity = new User();
        userEntity.setEmailId(signUpRequestDTO.getEmailId());
        userEntity.setPassword(signUpRequestDTO.getPassword());

        
        userEntity = userRepository.save(userEntity);

        // generate jwt
        String token = jwtUtils.generateJwt(userEntity);

        Map<String , Object> data = new HashMap<>();
        data.put("accessToken", token);

        apiResponse.setData(data);

        // return
        return apiResponse;
    }

    public APIResponse login(LoginRequestDTO loginRequestDTO) {

        APIResponse apiResponse = new APIResponse();

      
        SecurityProperties.User user = userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(), loginRequestDTO.getPassword());

     
        if(user == null){
            apiResponse.setData("User login failed");
            return apiResponse;
        }

        
        String token = jwtUtils.generateJwt(user);

        Map<String , Object> data = new HashMap<>();
        data.put("accessToken", token);

        apiResponse.setData(data);

        return apiResponse;
    }
}
