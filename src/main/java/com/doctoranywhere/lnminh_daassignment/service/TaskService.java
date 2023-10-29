package com.doctoranywhere.lnminh_daassignment.service;

import com.doctoranywhere.lnminh_daassignment.entity.TaskEntity;
import com.doctoranywhere.lnminh_daassignment.payload.request.TaskRequest;
import com.doctoranywhere.lnminh_daassignment.payload.response.BaseResponse;

import java.util.List;

public interface TaskService {

    /**
     *  Create task
     */
    TaskEntity createTask(TaskRequest taskRequest);

    /**
     *  Get all tasks
     */
    List<TaskEntity> getAllTasks();

    /**
     *
     * Get task by id
     */
    TaskEntity getTaskById(Long taskId);

    /**
     * Update task by id
     */
    TaskEntity updateTask(TaskRequest taskRequest);

    /**
     * Delete task by id
     */
    void deleteTask(Long taskId);
}
