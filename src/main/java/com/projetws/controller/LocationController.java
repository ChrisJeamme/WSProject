package com.projetws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetws.model.Location;
import com.projetws.model.LocationRepository;

import io.swagger.annotations.Api;

@Controller
@Api(value = "locations")
@RequestMapping("/location")
public class LocationController
{

	@Autowired
	LocationRepository locationRepository;

	@RequestMapping("/all")
	public String getAllLocations(Model m)
	{
		List<Location> locations = locationRepository.findAll();
		
		m.addAttribute("locations", locations);
		return "locations";
	}
	
	@RequestMapping(value="/updateLocation", method=RequestMethod.POST)
	public String updateLocation(@RequestParam("locationId") Long locationId, 
								 @RequestParam("streetAddress") String streetAddress, 
								 @RequestParam("postalCode") String postalCode,
								 @RequestParam("city") String city,
								 @RequestParam("stateProvince") String stateProvince)
	{
		Location location = locationRepository.findByLocationId(locationId);
		if(location != null)
		{
			location.setStreetAddress(streetAddress);
			location.setPostalCode(postalCode);
			location.setCity(city);
			location.setStateProvince(stateProvince);
			locationRepository.save(location);
		}
		return "redirect:/location/all";
	}
	
}
