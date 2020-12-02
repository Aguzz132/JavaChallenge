package com.university.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.university.model.Course;
import com.university.model.Role;
import com.university.model.User;
import com.university.service.CourseService;
import com.university.service.RoleService;
import com.university.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("user/login");
		
		return model;
	}
	
	 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
	 public ModelAndView signup() {
		  ModelAndView model = new ModelAndView();
		  User user = new User();
		  model.addObject("user", user);
		  model.setViewName("user/signup");
		  
		  return model;
	 }
	
	@RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userExists = userService.findUserByUserName(user.getUserName());
		
		if(userExists != null) {
			bindingResult.rejectValue("userName", "error.user", "This DNI already exists!");
			model.addObject("msg", "This Dni already exists!");
		}
		
		if(bindingResult.hasErrors()) {
			model.setViewName("user/signup");
		}else {
			userService.saveUser(user);
			model.addObject("msg", "User has been registered succesfully!");
			model.addObject("user", new User());
			model.setViewName("user/login");
		}
		
		return model;
	}
	
	@RequestMapping(value = {"/index"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		Role adminRole =  roleService.findRoleByName("ADMIN");
		Role studentRole =  roleService.findRoleByName("STUDENT");
		Set<Role> roles = user.getRoles();
		model.addObject("userName", user.getFirstName() + " " + user.getLastName());
		model.addObject("admin", roles.stream().filter(adminRole::equals).findAny().orElse(null));
		model.addObject("student", roles.stream().filter(studentRole::equals).findAny().orElse(null));
		model.setViewName("user/index");
		return model;
	}

	
	@RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}
	
	@GetMapping("/enrollCourse/{id}")
	public String enrollCourse(@PathVariable (value = "id") int id, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		Course course = courseService.getCourse(id);
		Set<Course> setCourses = user.getCourses();
		setCourses.add(course);
		user.setCourses(setCourses);
		userService.saveUser(user);
		
		List<Course> listCourses = courseService.getAllCourses();
		
		for(Course c : setCourses) {
			listCourses.remove(c);
		}
		model.addAttribute("msg", "Successfully enrolled in the course \""+ course.getName() + "\"");
		model.addAttribute("listCourses", listCourses);
		return "student/list_courses";
		
	}
	
	@GetMapping("/unenrollCourse/{id}")
	public String unenrollCourse(@PathVariable (value = "id") int id, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		Course course = courseService.getCourse(id);
		Set<Course> setCourses = user.getCourses();
		setCourses.remove(course);
		user.setCourses(setCourses);
		userService.saveUser(user);
		model.addAttribute("listEnrolledCourses", user.getCourses());
		model.addAttribute("msg", "Successfully unenrolled of the course \""+ course.getName() + "\"");
		return "student/enrolled_courses";
	}
	
	@GetMapping("/showListCoursesToEnroll")
	public String showListCoursesToEnroll(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		Set<Course> setCourses = user.getCourses();
		List<Course> listCourses = courseService.getAllCourses();
		
		for(Course course : setCourses) {
			listCourses.remove(course);
		}
		
		model.addAttribute("listCourses", listCourses);
		return "student/list_courses";
	}

	@GetMapping("/showListEnrolledCourses")
	public String showListEnrolledCourses(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("listEnrolledCourses", user.getCourses());
		
		return "student/enrolled_courses";
	}
	
	
}
