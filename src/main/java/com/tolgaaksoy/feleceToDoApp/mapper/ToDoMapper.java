package com.tolgaaksoy.feleceToDoApp.mapper;

import com.tolgaaksoy.feleceToDoApp.dto.ToDoDto;
import com.tolgaaksoy.feleceToDoApp.models.ToDo;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ToDoMapper {
    @Named("toToDo")
    ToDo toToDo(ToDoDto toDoDto);

    @Named("toToDoDto")
    ToDoDto toToDoDto(ToDo toDo);

    @IterableMapping(qualifiedByName = "toToDoList")
    List<ToDo> toToDoList(List<ToDoDto> toDoDtoList);

    @IterableMapping(qualifiedByName = "toToDoDtoList")
    List<ToDoDto> toToDoDtoList(List<ToDo> toDos);
}

