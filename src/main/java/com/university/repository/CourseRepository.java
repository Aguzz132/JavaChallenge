package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
