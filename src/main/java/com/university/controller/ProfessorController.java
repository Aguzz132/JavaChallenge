package com.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.university.model.Professor;
import com.university.service.ProfessorService;

@Controller
public class ProfessorController {
	
	@Autowired
	private ProfessorService service;
	
	//Display list of professors
	@GetMapping("/showListProfessors")
	public String viewListProfessors(Model model) {
		model.addAttribute("listProfessors", service.getAllProfessors());
		return "list_professors";
	}
	
	@GetMapping("/showNewProfessorForm")
	public String showNewProfessorForm(Model model) {
		Professor professor = new Professor();
		model.addAttribute("professor", professor);
		return "new_professor";
	}
	
	@PostMapping("/saveProfessor")
	public String saveProfessor(@ModelAttribute("professor") Professor professor, Model model) {
		//save professor to database
		service.saveProfessor(professor);
		model.addAttribute("msgRegister", "Professor \""+ professor.getName() + " " + professor.getLastName() + "\" has been registered succesfully!");
		model.addAttribute("listProfessors", service.getAllProfessors());
		return "list_professors";
	}
	
	@GetMapping("/showFormForProfessorUpdate/{id}")
	public String showFormForProfessorUpdate(@PathVariable (value = "id") int id, Model model) {
		//get professor from the service
		Professor professor = service.getProfessor(id);
		
		//set professor as a model attribute to fill the form
		model.addAttribute("professor", professor);
		
		return "update_professor";
	}
	
	@GetMapping("/deleteProfessor/{id}")
	public String deleteProfessor(@PathVariable (value = "id") int id, Model model) {
		Professor professor = service.getProfessor(id);
		service.deleteProfessor(id);
		
		model.addAttribute("msgDeleted", "Professor \""+ professor.getName() + " " + professor.getLastName() + "\" has been deleted succesfully!");
		model.addAttribute("listProfessors", service.getAllProfessors());
		return "list_professors";
	}
}
