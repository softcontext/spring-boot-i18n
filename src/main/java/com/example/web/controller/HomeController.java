package com.example.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@Autowired
	MessageSource messageSource;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("who", "Client");
		return "main";
	}

	/*
	 * http://localhost:8080/locale?lang=ko
	 * http://localhost:8080/locale?lang=en
	 */
	@RequestMapping(path = "/locale")
	@ResponseBody
	public Object locale(Locale locale) {
		String lang = locale.toString();
		
		String title = messageSource.getMessage("main.title", null, locale);
		String hello = messageSource.getMessage("main.hello", null, locale);
		String home = messageSource.getMessage("main.home", null, locale);

		Map<String, String> map = new HashMap<>();
		map.put("lang", lang);
		map.put("title", title);
		map.put("hello", hello);
		map.put("home", home);
		
		return map;
	}
}
