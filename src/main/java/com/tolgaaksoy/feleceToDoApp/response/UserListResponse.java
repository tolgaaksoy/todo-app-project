package com.tolgaaksoy.feleceToDoApp.response;

import com.tolgaaksoy.feleceToDoApp.dto.UserDto;
import lombok.Data;
import java.util.List;

@Data
public class UserListResponse {
    private List<UserDto> userDtoList;
}