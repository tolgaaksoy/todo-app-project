package com.tolgaaksoy.feleceToDoApp.service;

import com.tolgaaksoy.feleceToDoApp.dto.ToDoDto;
import com.tolgaaksoy.feleceToDoApp.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    void deleteUser(long id);
    UserDto updateUser(UserDto userDto);
    UserDto getUser(long userId);
    UserDto getByUserName(String username);
    List<UserDto> getUsers();
    List<ToDoDto> getListByDateBetween(Date startDate, Date endDate);

}