package org.service;

import java.util.List;

import org.dto.UserDto;

public interface UserService {
    boolean validate(UserDto userDto);
    
    List<UserDto> selectListUser();
    
    boolean add(UserDto userDto);
    
    boolean delete(int id);
    
    boolean modify(UserDto userDto);
    
    UserDto selectById(int id);
}
