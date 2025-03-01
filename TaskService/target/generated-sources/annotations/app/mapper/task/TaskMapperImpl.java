package app.mapper.task;

import app.dto.CreateTaskDto;
import app.dto.TaskDto;
import app.dto.UpdateTaskDto;
import app.entity.Status;
import app.entity.Task;
import app.entity.Task.TaskBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-01T18:10:13+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toEntity(TaskDto dto) {
        if ( dto == null ) {
            return null;
        }

        TaskBuilder task = Task.builder();

        task.id( dto.id() );
        task.title( dto.title() );
        task.description( dto.description() );
        task.status( dto.status() );
        task.userId( dto.userId() );

        return task.build();
    }

    @Override
    public TaskDto toDto(Task entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String description = null;
        Status status = null;
        Long userId = null;

        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        status = entity.getStatus();
        userId = entity.getUserId();

        TaskDto taskDto = new TaskDto( id, title, description, status, userId );

        return taskDto;
    }

    @Override
    public List<Task> toEntity(List<TaskDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( dtoList.size() );
        for ( TaskDto taskDto : dtoList ) {
            list.add( toEntity( taskDto ) );
        }

        return list;
    }

    @Override
    public List<TaskDto> toDto(List<Task> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TaskDto> list = new ArrayList<TaskDto>( entityList.size() );
        for ( Task task : entityList ) {
            list.add( toDto( task ) );
        }

        return list;
    }

    @Override
    public Task toEntity(CreateTaskDto dto) {
        if ( dto == null ) {
            return null;
        }

        TaskBuilder task = Task.builder();

        task.title( dto.title() );
        task.description( dto.description() );
        task.userId( dto.userId() );

        return task.build();
    }

    @Override
    public Task toEntity(UpdateTaskDto dto) {
        if ( dto == null ) {
            return null;
        }

        TaskBuilder task = Task.builder();

        task.title( dto.title() );
        task.description( dto.description() );
        task.status( dto.status() );
        task.userId( dto.userId() );

        return task.build();
    }

    @Override
    public Task toEntity(Long id, UpdateTaskDto dto) {
        if ( id == null && dto == null ) {
            return null;
        }

        TaskBuilder task = Task.builder();

        if ( id != null ) {
            task.id( id );
        }
        if ( dto != null ) {
            task.title( dto.title() );
            task.description( dto.description() );
            task.status( dto.status() );
            task.userId( dto.userId() );
        }

        return task.build();
    }

    @Override
    public void update(Task task, Task taskFromDto) {
        if ( taskFromDto == null ) {
            return;
        }

        task.setId( taskFromDto.getId() );
        task.setTitle( taskFromDto.getTitle() );
        task.setDescription( taskFromDto.getDescription() );
        task.setStatus( taskFromDto.getStatus() );
        task.setUserId( taskFromDto.getUserId() );
    }
}
