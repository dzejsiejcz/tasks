package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;

import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;


    @GetMapping
    public List<TaskDto> getTasks(){
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "{taskId}")
    public Task getTask(@PathVariable Long taskId) throws ResponseStatusException {
        Optional<Task> optionalTask = service.getTask(taskId);
        if (optionalTask.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return taskMapper.mapToTask(optionalTask.get());
    }

    @DeleteMapping
    public void deleteTask(Long taskId) {

    }

    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping
    public void createTask(TaskDto taskDto) {

    }

}
