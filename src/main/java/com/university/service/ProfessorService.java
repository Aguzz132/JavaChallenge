package com.university.service;

import java.util.List;

import com.university.model.Professor;

public interface ProfessorService {
	
	List<Professor> getAllProfessors();
	void saveProfessor(Professor professor);
	Professor getProfessor(int id);
	public void deleteProfessor(int id);
	
}