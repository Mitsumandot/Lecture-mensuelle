package com.example.lecturemensuelle.dao.repositories;

import com.example.lecturemensuelle.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
