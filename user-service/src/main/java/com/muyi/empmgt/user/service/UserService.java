package com.muyi.empmgt.user.service;

import com.muyi.empmgt.user.dto.MenuDto;
import com.muyi.empmgt.user.dto.UserDto;
import com.muyi.empmgt.user.model.Menu;
import com.muyi.empmgt.user.model.User;

import java.util.Set;

public interface UserService {

    UserDto validateUser(UserDto userDto);
    Set<MenuDto> getMenuListByUserId(Integer userId);
}
