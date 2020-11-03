package com.tolgaaksoy.feleceToDoApp.request;

import com.tolgaaksoy.feleceToDoApp.dto.ToDoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ToDoRequest {
    @JsonProperty("ToDoDto")
    ToDoDto toDoDto;
}
