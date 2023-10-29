package com.doctoranywhere.lnminh_daassignment.repository;

import com.doctoranywhere.lnminh_daassignment.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
}
