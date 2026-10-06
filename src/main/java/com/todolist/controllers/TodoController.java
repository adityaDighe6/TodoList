package com.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todolist.entities.Todo;
import com.todolist.repos.TodoRepository;

@Controller
public class TodoController {
	@Autowired
	private TodoRepository todorepository;
	
	@GetMapping({"", "/", "/home"})
	public String showHomePage(Model model) {
		model.addAttribute("todos", todorepository.findAll());
		return "index";
	}
	
	@PostMapping("/add")
	public String add(@RequestParam String title) {
		
		Todo todonew = new Todo.builder()
				.title(title)
				.completed(false)
				.build();
		todorepository.save(todonew);
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id) {
		
		Todo existingtodo = todorepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Todo not found"));
		
		existingtodo.setCompleted(true);
		todorepository.save(existingtodo);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		Todo existingtodo = todorepository.findById(id)
		.orElseThrow(() -> new RuntimeException("Todo not found"));
		
		todorepository.delete(existingtodo);
		
		return "redirect:/";
	}
}
