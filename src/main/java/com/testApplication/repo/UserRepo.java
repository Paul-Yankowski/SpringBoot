package com.testApplication.repo;

import com.testApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User,Long> {
    User findUserByUserName(String userName);
}
