package com.touhid.todo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@RequestMapping(value="/list-todos", method=RequestMethod.GET )
	public String showTodoPage(ModelMap model){
		// model.addAttribute("todos", service.retrieveTodos("in28minutes"));
		model.addAttribute("todos", service.retrieveTodos());		
		return "list-todos";
	}
	
	@RequestMapping(value="/add-todo", method =RequestMethod.GET)
	public String addTodo() {
		return "add-todo";
	}
	
	@RequestMapping(value="/add-todo", method =RequestMethod.POST)
	public String addTodoNew(ModelMap model, @RequestParam String name, @RequestParam String desc) {
		service.addTodo(name, desc, new Date(), false); // for the moment its hard coded
		return "redirect:list-todos";
	}
}