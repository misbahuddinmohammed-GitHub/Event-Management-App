package com.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController 
{
	@Autowired
	private RegistrationService registrationService;
	
//	Inserting A Registration Into The Database
	@PostMapping
	public ResponseEntity<ResponseStructure<Registration>> createRegistration(@RequestBody Registration registration)
	{
		return registrationService.createRegistration(registration);
	}
	
	
//	Fetch All The Registration Records
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistration()
	{
		return registrationService.getAllRegistration();
	}
	
//	Fetch The Registration Record By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Registration>> getRegistrationById(@PathVariable Integer id)
	{
		return registrationService.getRegistrationById(id);
	}
	
//	Delete The Registration From The Database Using Id
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Registration>> cancelRegistration(@PathVariable Integer id)
	{
		return registrationService.cancelRegistration(id);
	}
	
//	Fetch Registration By Event Id
	@GetMapping("/eventId/{id}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByEventId(@PathVariable Integer id)
	{
		return registrationService.getRegistrationByEventId(id);
	}
	
//	Fetch Registration By Attendee Id
	@GetMapping("/attendeeId/{id}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendee(@PathVariable Integer id)
	{
		return registrationService.getRegistrationByAttendee(id);
	}
}
