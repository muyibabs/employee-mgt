package com.muyi.empmgt.user.controller;

import com.muyi.empmgt.user.dto.MenuDto;
import com.muyi.empmgt.user.dto.UserDto;
import com.muyi.empmgt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/validate-user")
    public UserDto validateUser(@RequestBody UserDto userDto){
        return userService.validateUser(userDto);
    }

    @GetMapping("/get-menus/{user-id}")
    public Set<MenuDto> getMenusByUserId(@PathVariable("user-id") Integer userId){
        return userService.getMenuListByUserId(userId);
    }
//    public static void main(String[] args) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String bCryptedPassword = bCryptPasswordEncoder.encode("exec");
//        String bCryptedPassword2 = bCryptPasswordEncoder.encode("manager");
//        System.out.println(bCryptedPassword);
//        System.out.println(bCryptedPassword2);
//    }
}
