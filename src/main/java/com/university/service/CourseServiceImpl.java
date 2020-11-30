package com.university.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.model.Course;
import com.university.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository repository;

	@Override
	public List<Course> getAllCourses() {
		return repository.findAll();
	}
	
//	@Override
//	public List<Course> getCourseAvailable() {
//		
//	}

	@Override
	public void saveCourse(Course course) {
		this.repository.save(course);
		
	}

	@Override
	public Course getCourse(int id) {
		Optional<Course> optional = repository.findById(id);
		Course course = null;
		
		if(optional.isPresent()) {
			course = optional.get();
		}else {
			throw new RuntimeException("Course not found");
		}
		
		return course;
	}

	@Override
	public void deleteCourse(int id) {
		this.repository.deleteById(id);
		
	}

//	@Override
//	public List<Course> getCourseAvailable() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

}
