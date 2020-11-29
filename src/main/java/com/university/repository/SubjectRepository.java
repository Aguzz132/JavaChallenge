package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
