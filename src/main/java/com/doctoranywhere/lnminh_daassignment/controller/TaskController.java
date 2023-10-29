package com.doctoranywhere.lnminh_daassignment.controller;

import com.doctoranywhere.lnminh_daassignment.entity.TaskEntity;
import com.doctoranywhere.lnminh_daassignment.payload.request.TaskRequest;
import com.doctoranywhere.lnminh_daassignment.payload.response.BaseResponse;
import com.doctoranywhere.lnminh_daassignment.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Validated
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping("")
    public ResponseEntity<BaseResponse> createTask(@RequestBody @Valid TaskRequest taskRequest) {
        TaskEntity savedTask = taskService.createTask(taskRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(201);
        baseResponse.setMessage("Create Task");
        baseResponse.setData(savedTask);

        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> getAllTasks() {
        List<TaskEntity> listTasks = taskService.getAllTasks();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setMessage("Find all tasks");
        baseResponse.setData(listTasks);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getTaskById(@PathVariable("id")
                                                    @Positive(message = "Task ID must be a positive number") Long id) {
        TaskEntity existingTask = taskService.getTaskById(id);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setMessage("Get task by id");
        baseResponse.setData(existingTask);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> updateTaskById(@PathVariable("id")
                                                       @Positive(message = "Task ID must be a positive number") Long id,
                                                       @RequestBody TaskRequest taskRequest) {
        taskRequest.setId(id);
        TaskEntity updatedTask = taskService.updateTask(taskRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setMessage("Update task by id");
        baseResponse.setData(updatedTask);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> deleteTaskById(@PathVariable("id")
                                                       @Positive(message = "Task ID must be a positive number") Long id) {
        taskService.deleteTask(id);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setMessage("Delete task successfully");
        baseResponse.setData(true);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
