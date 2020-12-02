package com.university.service;

import java.util.List;

import com.university.model.Course;

public interface CourseService {
	
	List<Course> getAllCourses();
	void saveCourse(Course course);
	Course getCourse(int id);
	public void deleteCourse(int id);
}
