package com.akhadam.kitabi.ws.controller;

import com.akhadam.kitabi.dto.UserDto;
import com.akhadam.kitabi.exception.UserException;
import com.akhadam.kitabi.exception.responses.ErrorMessages;
import com.akhadam.kitabi.requests.UserRequest;
import com.akhadam.kitabi.responses.UserResponse;
import com.akhadam.kitabi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

        if (userRequest.getName().isEmpty())
            throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserDto createUser = userService.createUser(userDto);
        UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> findByUserId(@PathVariable String userId) {

        UserDto userDto = userService.findByUserId(userId);
        UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable String userId, @RequestBody UserRequest userRequest) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        UserDto updatedUser = userService.update(userId, userDto);
        UserResponse userResponse = modelMapper.map(updatedUser, UserResponse.class);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);

    }

    @GetMapping
    public List<UserResponse> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "limit", defaultValue = "15") int limit) {

        List<UserResponse> usersResponse = new ArrayList<>();
        List<UserDto> users = userService.findAll(page, limit);

        for (UserDto userDto : users) {
            UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);
            usersResponse.add(userResponse);
        }

        return usersResponse;
    }

    @GetMapping("/name")
    public List<UserResponse> findByName(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "limit", defaultValue = "15") int limit,
                                         @RequestParam(value = "name", defaultValue = "") String name) {

        List<UserResponse> usersResponse = new ArrayList<>();
        List<UserDto> users = userService.findByName(page, limit, name);
        for (UserDto userDto : users) {
            UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);
            usersResponse.add(userResponse);
        }
        return usersResponse;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> delete(@PathVariable String userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable String email) {
        UserDto userDto = userService.findByEmail(email);
        UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }
}
