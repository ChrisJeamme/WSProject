package com.projetws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController
{
	@RequestMapping("/all")
	public String hub()
	{
		return "hubPage";
	}
}
