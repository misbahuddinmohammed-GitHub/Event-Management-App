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
import com.eventmanagement.entity.Event;
import com.eventmanagement.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController
{
	@Autowired
	private EventService eventService;
	
//	Inserting an Event into the Database
	@PostMapping
	public ResponseEntity<ResponseStructure<Event>> createEvent(@RequestBody Event event)
	{
		return eventService.createEvent(event);
	}
	
	
	
//	Fetch All The Event Records
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent()
	{
		return eventService.getAllEvent();
	}
	
	
//	Fetch The Event By The Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Event>> getEventById(@PathVariable Integer id)
	{
		return eventService.getEventById(id);
	}
	
	
//	Update An Event In The Database
	@PutMapping
	public ResponseEntity<ResponseStructure<Event>> updateEvent(@RequestBody Event event)
	{
		return eventService.updateEvent(event);
	}
	
	
//	Deleting An Event From The Database By Id
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Event>> deleteEvent(@PathVariable Integer id)
	{
		return eventService.deleteEvent(id);
	}
	
//	Get Attendee By Event Id From The Database
	@GetMapping("/eventId/{id}")
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeeByEventId(@PathVariable Integer id)
	{
		return eventService.getAttendeeByEventId(id);
	}
	
//	Get Event By Organizer Id From The Database
	@GetMapping("/organizerId/{id}")
	public ResponseEntity<ResponseStructure<List<Event>>> getEventByOrganizerId(@PathVariable Integer id)
	{
		return eventService.getEventByOrganizerId(id);
	}
	
//	Pagination And Sorting
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Event>>> getEventByPaginationAndSorting(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field)
	{
		return eventService.getEventByPaginationAndSorting(pageNumber, pageSize, field);
	}
}
