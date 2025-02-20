package app.aop.mapper.task;

import app.aop.dto.CreateTaskDto;
import app.aop.dto.TaskDto;
import app.aop.dto.UpdateTaskDto;
import app.aop.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<Task, TaskDto> {

    Task toEntity(CreateTaskDto dto);

    Task toEntity(UpdateTaskDto dto);

    Task toEntity(Long id, UpdateTaskDto dto);

    void update(@MappingTarget Task task, Task taskFromDto);
}
