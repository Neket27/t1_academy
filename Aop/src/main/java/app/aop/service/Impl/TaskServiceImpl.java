package app.aop.service.Impl;

import app.aop.aspect.annotation.CustomLogging;
import app.aop.dto.CreateTaskDto;
import app.aop.dto.TaskDto;
import app.aop.dto.UpdateTaskDto;
import app.aop.entity.Task;
import app.aop.exception.NotFoundException;
import app.aop.mapper.task.TaskMapper;
import app.aop.repository.TaskRepository;
import app.aop.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@CustomLogging
public class TaskServiceImpl implements TaskService {

    @Value("${task.limit-downloads}")
    private Integer limit;

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskDto create(CreateTaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        task = taskRepository.save(task);
        return taskMapper.toDto(task);

    }

    @Override
    @Transactional
    public TaskDto getById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException(Task.class, id));
        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public TaskDto update(Long id, UpdateTaskDto dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException(Task.class, id));
        Task taskFromDto = taskMapper.toEntity(id, dto);

        taskMapper.update(task, taskFromDto);
        taskRepository.save(task);

        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        taskRepository.findById(id).ifPresentOrElse(
                taskRepository::delete,
                () -> {
                    throw new NotFoundException(Task.class, id);
                }
        );
    }

    @Override
    @Transactional
    public List<TaskDto> getList() {
        List<TaskDto> allTasks = new ArrayList<>();
        int page = 0;

        do {
            Pageable pageable = PageRequest.of(page, limit);
            List<Task> tasks = taskRepository.findAll(pageable).toList();

            if (tasks.isEmpty())
                break;

            allTasks.addAll(taskMapper.toDto(tasks));
            page++;
        }
        while (true);

        return allTasks;
    }
}
