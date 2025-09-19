package com.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.service.AttendeeService;

@RestController
@RequestMapping("/attendee")
public class AttendeeController 
{
	@Autowired
	private AttendeeService attendeeService;
	
//	Inserting A Attendee Into The Database
	@PostMapping
	public ResponseEntity<ResponseStructure<Attendee>> registerAttendee(@RequestBody Attendee attendee)
	{
		return attendeeService.registerAttendee(attendee);
	}
	
//	Fetch All The Attendee Records
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttendee()
	{
		return attendeeService.getAllAttendee();
	}
	
//	Fetch The Attendee Record By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeeById(@PathVariable Integer id)
	{
		return attendeeService.getAttendeeById(id);
	}
	
//	Update The Attendee In The Database
	@PutMapping
	public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(@RequestBody Attendee attendee)
	{
		return attendeeService.updateAttendee(attendee);
	}
	
//	Delete The Attendee From The Database Using Id
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Attendee>> deleteAttendee(@PathVariable Integer id)
	{
		return attendeeService.deleteAttendee(id);
	}
	
	
//	Fetch The Registration By Attendee
	@GetMapping("/attendeeId/{id}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendee(@PathVariable Integer id)
	{
		return attendeeService.getRegistrationByAttendee(id);
	}

	
//	Fetch The Attendee By Contact
	@GetMapping("/attendeeContact/{contact}")
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeeByContact(@PathVariable Long contact)
	{
		return attendeeService.getAttendeeByContact(contact);
	}
	
	
//	Pagination And Sorting
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Attendee>>> getAttendeeByPaginationAndSorting(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field)
	{
		return attendeeService.getAttendeeByPaginationAndSorting(pageNumber, pageSize, field);
	}
}
