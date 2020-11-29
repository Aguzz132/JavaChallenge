package com.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.university.model.Subject;
import com.university.service.ProfessorService;
import com.university.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ProfessorService professorService;
	
	//Display list of professors
	@GetMapping("/showListSubjects")
	public String viewListProfessors(Model model) {
		model.addAttribute("listSubjects", subjectService.getAllSubjects());
		model.addAttribute("listProfessors", professorService.getAllProfessors());
		return "list_subjects";
	}
	
	@GetMapping("/showNewSubjectForm")
	public String showNewProfessorForm(Model model) {
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
		model.addAttribute("listProfessors", professorService.getAllProfessors());
		return "new_subject";
	}
	
	@PostMapping("/saveSubject")
	public String saveProfessor(@ModelAttribute("subject") Subject subject) {
		//save professor to database
		subjectService.saveSubject(subject);
		return "redirect:/showListSubjects";
	}
	
	@GetMapping("/showFormForSubjectUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") int id, Model model) {
		//get professor from the service
		Subject subject = subjectService.getSubject(id);
		
		//set professor as a model attribute to fill the form
		model.addAttribute("subject", subject);
		model.addAttribute("listProfessors", professorService.getAllProfessors());
		
		return "update_subject";
	}
	
	@GetMapping("/deleteSubject/{id}")
	public String deleteSubject(@PathVariable (value = "id") int id) {
		
		subjectService.deleteSubject(id);
		
		return "redirect:/showListSubjects";
	}
	
}
