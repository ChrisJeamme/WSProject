package com.projetws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetws.model.Country;
import com.projetws.model.CountryRepository;

import io.swagger.annotations.Api;

@Controller
@Api(value = "countries")
@RequestMapping("/country")
public class CountryController
{

	@Autowired
	CountryRepository countryRepository;

	@RequestMapping("/all")
	public String getAllCountries(Model m)
	{
		List<Country> countries = countryRepository.findAll();
		m.addAttribute("countries", countries);
		return "countries";
	}
	
	@RequestMapping(value="/updateCountry", method=RequestMethod.POST)
	public String updateCountry(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName)
	{
		List<Country> countries = countryRepository.findByCountryName(oldName);
		for (Country country : countries)
		{
			country.setCountryName(newName);
		}
		countryRepository.saveAll(countries);
		return "redirect:/country/all";
	}
	
}
