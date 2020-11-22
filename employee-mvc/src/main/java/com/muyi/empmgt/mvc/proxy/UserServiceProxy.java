package com.muyi.empmgt.mvc.proxy;

import com.muyi.empmgt.mvc.dto.MenuDto;
import com.muyi.empmgt.mvc.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@FeignClient(name = "user-service")
public interface UserServiceProxy {

    @PostMapping("/validate-user")
    public UserDto validateUser(@RequestBody UserDto userDto);

    @GetMapping("/get-menus/{user-id}")
    public Set<MenuDto> getMenusByUserId(@PathVariable("user-id") Integer userId);

}
