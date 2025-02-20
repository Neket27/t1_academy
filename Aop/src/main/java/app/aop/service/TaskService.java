package app.aop.service;

import app.aop.dto.CreateTaskDto;
import app.aop.dto.TaskDto;
import app.aop.dto.UpdateTaskDto;

import java.util.List;

public interface TaskService {

    TaskDto create(CreateTaskDto dto);

    TaskDto getById(Long id);

    TaskDto update(Long id, UpdateTaskDto dto);

    void remove(Long id);

    List<TaskDto> getList();
}
