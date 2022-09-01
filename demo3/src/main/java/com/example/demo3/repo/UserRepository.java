/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo3.repo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author admin
 */
public interface UserRepository extends CrudRepository<SecurityProperties.User, Long> {
    SecurityProperties.User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);
}