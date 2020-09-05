package com.touhid.todo;

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
		model.addAttribute("todos", service.retrieveTodos("in28Minutes"));
		return "list-todos";
	}
}