package com.marcosparreiras.nlw_14.modules.students.repositories;

import com.marcosparreiras.nlw_14.modules.students.entities.StudentEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
  Optional<StudentEntity> findByEmail(String email);
}
