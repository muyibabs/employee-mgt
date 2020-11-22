package com.muyi.empmgt.user.service;

import com.muyi.empmgt.user.dto.MenuDto;
import com.muyi.empmgt.user.dto.UserDto;
import com.muyi.empmgt.user.model.Menu;
import com.muyi.empmgt.user.model.Role;
import com.muyi.empmgt.user.model.User;
import com.muyi.empmgt.user.repository.MenuRepository;
import com.muyi.empmgt.user.repository.RoleRepository;
import com.muyi.empmgt.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public UserDto validateUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        if(user==null)
            return null;
        //encrypt userDto password
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean passwordIsValid = bCryptPasswordEncoder.matches(String.valueOf(userDto.getPassword()), user.getPassword());
        if(!passwordIsValid)
            return null;

        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    @Override
    public Set<MenuDto> getMenuListByUserId(Integer userId) {
        Set<MenuDto> menuDtos = new TreeSet<>();
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent())
            return menuDtos;
        User user = optionalUser.get();
        Set<Role> roles = user.getRoles();
        Set<Menu> menus = new HashSet<>();
        for(Role role: roles){
            menus.addAll(role.getMenus());
        }
        //Map menus to Dtos --check how to sort HashSet or TreeSet
        for(Menu menu: menus){
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menu, menuDto);
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }
}
