package com.jks.details.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jks.details.model.EmployeeNote;

@Repository
public interface EmployeeNoteRepository extends JpaRepository<EmployeeNote, Long> {

}
