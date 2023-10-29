package com.doctoranywhere.lnminh_daassignment.mapper;

import com.doctoranywhere.lnminh_daassignment.entity.TaskEntity;
import com.doctoranywhere.lnminh_daassignment.payload.request.TaskRequest;
import org.springframework.stereotype.Component;

@Component
public class TaskRequestMapper implements GenericMapper<TaskEntity, TaskRequest>{

    @Override
    public TaskEntity toEntity(TaskRequest taskDto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setCompleted(taskDto.getCompleted());
        return taskEntity;
    }

    @Override
    public TaskRequest toDTO(TaskEntity entity) {
        return new TaskRequest(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getCompleted()
        );
    }
}
