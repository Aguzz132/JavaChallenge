package com.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.university.model.Course;
import com.university.model.Professor;
import com.university.service.ProfessorService;
import com.university.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/showListCourses")
	public String viewListCourses(Model model) {
		model.addAttribute("listCourses", courseService.getAllCourses());
		return "list_courses";
	}
	
	
	@GetMapping("/showNewCourseForm")
	public String showNewCourseForm(Model model) {
		Course course = new Course();
		List<Professor> list = professorService.getAllProfessors();
		
		if (list.isEmpty()) {
			model.addAttribute("msgError", "You have to add a professor first!");
			return "list_courses";
		}else {
			
			model.addAttribute("course", course);
			model.addAttribute("listProfessors", professorService.getAllProfessors());
			return "new_course";
		}
		
	}
	
	@PostMapping("/saveCourse")
	public String saveCourse(@ModelAttribute("course") Course course, Model model) {
		//save professor to database
		courseService.saveCourse(course);
		model.addAttribute("msgRegister", "Course \""+ course.getName() +"\" has been registered succesfully!");
		model.addAttribute("listCourses", courseService.getAllCourses());
		return "list_courses";
	}
	
	@GetMapping("/showFormForCourseUpdate/{id}")
	public String showFormForCourseUpdate(@PathVariable (value = "id") int id, Model model) {
		Course course = courseService.getCourse(id);
		
		//set professor as a model attribute to fill the form
		model.addAttribute("course", course);
		model.addAttribute("listProfessors", professorService.getAllProfessors());
		
		return "update_course";
	}
	
	@GetMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable (value = "id") int id, Model model) {
		Course course = courseService.getCourse(id);
		courseService.deleteCourse(id);
		
		model.addAttribute("msgDeleted", "Course \""+ course.getName() +"\" has been deleted succesfully!");
		model.addAttribute("listCourses", courseService.getAllCourses());
		
		return "list_courses";
	}
	
	@GetMapping("/showDescriptionCourse/{id}")
	public String viewListEnrollCourses(@PathVariable (value = "id") int id, Model model) {
		model.addAttribute("course", courseService.getCourse(id));
		return "student/course_description";
	}
}
