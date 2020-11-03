package com.tolgaaksoy.feleceToDoApp.request;

import com.tolgaaksoy.feleceToDoApp.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRequest {
    @JsonProperty("UserDto")
    UserDto userDto;
}