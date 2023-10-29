package com.doctoranywhere.lnminh_daassignment.repository;

import com.doctoranywhere.lnminh_daassignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}
