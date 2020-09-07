package com.touhid.todo;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
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
	public String addTodo(ModelMap model) {
		model.addAttribute("todo", new Todo());
		return "add-todo";
	}
	
	@RequestMapping(value="/delete-todo", method=RequestMethod.GET)
	public String delteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	/*public String addTodoNew(ModelMap model, @RequestParam String name, @RequestParam String desc) {
		service.addTodo(name, desc, new Date(), false); // for the moment its hard coded
		return "redirect:list-todos";
	}*/
	@RequestMapping(value="/add-todo", method =RequestMethod.POST)
	public String addTodoNew( @Valid @ModelAttribute("todo") Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		service.addTodo(todo.getUser(), todo.getDesc(), new Date(), false);
		return "redirect:/list-todos";
	}
}