package com.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.university.model.Course;
import com.university.service.ProfessorService;
import com.university.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ProfessorService professorService;
	
	//Display list of professors
	@GetMapping("/showListCourses")
	public String viewListCourses(Model model) {
		model.addAttribute("listCourses", courseService.getAllCourses());
		return "list_courses";
	}
	
	
	@GetMapping("/showNewCourseForm")
	public String showNewCourseForm(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		model.addAttribute("listProfessors", professorService.getAllProfessors());
		return "new_course";
	}
	
	@PostMapping("/saveCourse")
	public String saveCourse(@ModelAttribute("course") Course course) {
		//save professor to database
		courseService.saveCourse(course);
		return "redirect:/showListCourses";
	}
	
	@GetMapping("/showFormForCourseUpdate/{id}")
	public String showFormForCourseUpdate(@PathVariable (value = "id") int id, Model model) {
		//get professor from the service
		Course course = courseService.getCourse(id);
		
		//set professor as a model attribute to fill the form
		model.addAttribute("course", course);
		model.addAttribute("listProfessors", professorService.getAllProfessors());
		
		return "update_course";
	}
	
	@GetMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable (value = "id") int id) {
		
		courseService.deleteCourse(id);
		
		return "redirect:/showListCourses";
	}
	
	@GetMapping("/showDescriptionCourse/{id}")
	public String viewListEnrollCourses(@PathVariable (value = "id") int id, Model model) {
		model.addAttribute("course", courseService.getCourse(id));
		return "student/course_description";
	}
}
