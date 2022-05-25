package ru.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.app.service.AppService;

@Controller
@RequestMapping("")
public class IndexController {


	@GetMapping("/loginForm")
	public String loginPage() {
		return "/loginForm";
	}
}