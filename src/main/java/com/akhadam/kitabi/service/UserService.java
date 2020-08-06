/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhadam.kitabi.service;

import com.akhadam.kitabi.dto.UserDto;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author AKH
 */

@Service
public interface UserService {
    
    UserDto createUser(UserDto userDto);

    UserDto getUser(String email);

    UserDto findByUserId(String userId);

    UserDto update(String userId, UserDto userDto);

    void delete(String userId);

    List<UserDto> findAll(int page, int limit);

    List<UserDto> findByName(int page, int limit, String name);
 
}
