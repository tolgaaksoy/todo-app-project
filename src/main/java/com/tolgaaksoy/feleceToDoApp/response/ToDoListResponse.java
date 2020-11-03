package com.tolgaaksoy.feleceToDoApp.response;

import com.tolgaaksoy.feleceToDoApp.dto.ToDoDto;
import lombok.Data;

import java.util.List;

@Data
public class ToDoListResponse {
    List<ToDoDto> toDoList;
}
