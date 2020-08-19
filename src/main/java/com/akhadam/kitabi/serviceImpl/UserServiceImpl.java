/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhadam.kitabi.serviceImpl;

import com.akhadam.kitabi.dto.UserDto;
import com.akhadam.kitabi.entity.UserEntity;
import com.akhadam.kitabi.repository.UserRepository;
import com.akhadam.kitabi.service.UserService;
import com.akhadam.kitabi.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AKH
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils util;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public UserDto createUser(UserDto user) {

        // check if user exist
        UserEntity foundedUser = userRepository.findByEmail(user.getEmail());
        if (foundedUser != null) throw new RuntimeException("User Already exist");

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        // Encrypter le mot de passe
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // Générer un ID public
        userEntity.setUserId(util.generateId(30));

        UserEntity newUser = userRepository.save(userEntity);

        UserDto userDto = modelMapper.map(newUser, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto findByEmail(String email) {
        UserEntity foundedUser = userRepository.findByEmail(email);
        if (foundedUser == null) throw new RuntimeException("user not found");
        UserDto userDto = modelMapper.map(foundedUser, UserDto.class);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserDto findByUserId(String userId) {
        UserEntity foundedUser = userRepository.findByUserId(userId);
        if (foundedUser == null) throw new RuntimeException("user not found");
        UserDto userDto = modelMapper.map(foundedUser, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto update(String userId, UserDto userDto) {
        UserEntity foundedUser = userRepository.findByUserId(userId);
        if (userId == null) throw new RuntimeException("User not found");
        foundedUser.setName(userDto.getName());
        UserEntity updatedUser = userRepository.save(foundedUser);
        UserDto user = modelMapper.map(updatedUser, UserDto.class);
        return user;
    }

    @Override
    public List<UserDto> findAll(int page, int limit) {
        // By default page in Spring boot starts with 0 but we want to start with 1
        if (page > 0) page -= 1;

        List<UserDto> usersDto = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> userEntityPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = userEntityPage.getContent();

        for (UserEntity userEntity : users) {
            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            usersDto.add(userDto);
        }

        return usersDto;
    }

    @Override
    public List<UserDto> findByName(int page, int limit, String name) {
        if (page > 0) page -= 1;

        List<UserDto> usersDto = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> userEntityPage = userRepository.findByName(pageableRequest,name);
        List<UserEntity> users = userEntityPage.getContent();

        for (UserEntity userEntity : users) {
            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            usersDto.add(userDto);
        }

        return usersDto;
    }

    @Override
    public void delete(String userId) {
        UserEntity foundedUser = userRepository.findByUserId(userId);
        if (foundedUser == null) throw new RuntimeException("User not found");
        userRepository.delete(foundedUser);
    }

}
