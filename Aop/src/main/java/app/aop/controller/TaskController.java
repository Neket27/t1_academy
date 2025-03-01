package app.aop.controller;

import app.aop.controller.advice.annotation.CustomExceptionHandler;
import app.aop.dto.CreateTaskDto;
import app.aop.dto.TaskDto;
import app.aop.dto.UpdateTaskDto;
import app.aop.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@CustomExceptionHandler
public class TaskController {

    private final TaskService taskService;

    @PostMapping()
    public TaskDto create(@RequestBody CreateTaskDto dto) {
        return taskService.create(dto);
    }

    @GetMapping("/{id}")
    public TaskDto get(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PutMapping("/{id}")
    public TaskDto update(@PathVariable Long id, @RequestBody UpdateTaskDto dto) {
        return taskService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        taskService.remove(id);
        return HttpStatus.OK;
    }

    @GetMapping()
    public List<TaskDto> list() {
        return taskService.getList();
    }

}
