package com.university.service;

import java.util.List;

import com.university.model.Subject;

public interface SubjectService {
	
	List<Subject> getAllSubjects();
	void saveSubject(Subject subject);
	Subject getSubject(int id);
	public void deleteSubject(int id);
}
