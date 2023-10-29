package com.doctoranywhere.lnminh_daassignment.service.impl;

import com.doctoranywhere.lnminh_daassignment.entity.TaskEntity;
import com.doctoranywhere.lnminh_daassignment.exception.ResourceNotFoundException;
import com.doctoranywhere.lnminh_daassignment.mapper.TaskRequestMapper;
import com.doctoranywhere.lnminh_daassignment.payload.request.TaskRequest;
import com.doctoranywhere.lnminh_daassignment.repository.TaskRepository;
import com.doctoranywhere.lnminh_daassignment.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private TaskRequestMapper taskRequestMapper;

    @Override
    public TaskEntity createTask(TaskRequest taskRequest) {
        TaskEntity taskEntity = taskRequestMapper.toEntity(taskRequest);
        return taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity getTaskById(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task","id",taskId)
        );
        return task;
    }

    @Override
    public TaskEntity updateTask(TaskRequest taskRequest) {
        TaskEntity existingTask = taskRepository.findById(taskRequest.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Task","id",taskRequest.getId())
        );
        existingTask.setTitle(taskRequest.getTitle() != null ? taskRequest.getTitle() : existingTask.getTitle());
        existingTask.setDescription(taskRequest.getDescription() !=null ? taskRequest.getDescription() : existingTask.getDescription());
        existingTask.setCompleted(taskRequest.getCompleted() !=null ? taskRequest.getCompleted() : existingTask.getCompleted());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        TaskEntity existingTask = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task","id",taskId)
        );
        taskRepository.deleteById(taskId);
    }
}
