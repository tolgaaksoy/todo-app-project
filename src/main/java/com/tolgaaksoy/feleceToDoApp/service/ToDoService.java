package com.tolgaaksoy.feleceToDoApp.service;

import com.tolgaaksoy.feleceToDoApp.dto.ToDoDto;

import java.util.Date;
import java.util.List;

public interface ToDoService {
    ToDoDto addToDo(ToDoDto toDoDto);
    void deleteToDo(long id);
    ToDoDto updateToDo(ToDoDto toDoDto);
    ToDoDto getToDo(long id);
    List<ToDoDto> getList();
    List<ToDoDto> getListByUserId(long userId);
    List<ToDoDto> getListByDate(Long id,Date startDate, Date endDate);
    List<ToDoDto> getListByContainingTitle(String title);

}
