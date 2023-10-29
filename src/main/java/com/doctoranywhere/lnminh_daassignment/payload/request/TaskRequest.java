package com.doctoranywhere.lnminh_daassignment.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskRequest {

    /**
     *  id of the task
     */
    private Long id;

    /**
     *  title of the task
     */
    @NotEmpty
    private String title;

    /**
     *  description of the task
     */
    @NotEmpty
    private String description;

    /**
     *  flag indicating whether the task has been completed
     */
    @NotNull
    private Boolean completed;

}
