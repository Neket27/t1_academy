package app.aop.dto;

public record UpdateTaskDto(
        String title,
        String description,
        Long userId
) {
}
