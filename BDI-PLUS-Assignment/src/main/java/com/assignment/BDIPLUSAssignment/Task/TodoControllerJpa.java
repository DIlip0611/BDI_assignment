package com.assignment.BDIPLUSAssignment.Task;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

	private TodoRepository repository;

	public TodoControllerJpa(TodoRepository repository) {
		super();
		this.repository = repository;
	}

	@RequestMapping("list-todos")
	public String listAlltodos(ModelMap model) {
		String username = getLoggedInusername(model);
		List<Todo> todos = repository.findByUsername(username);
		model.put("todos", todos);
		return "listTodos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInusername(model);
		Todo todo = new Todo(0, username, "");
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}

		String username = getLoggedInusername(model);
		todo.setUserame(username);
		repository.save(todo);
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deletetodo(@RequestParam int id) {
		repository.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String updatetodo(@RequestParam int id, ModelMap model) {
		Todo todo = repository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}

		String username = getLoggedInusername(model);
		todo.setUserame(username);
		repository.save(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInusername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
