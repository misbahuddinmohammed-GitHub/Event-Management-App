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
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.service.VenueService;

@RestController
@RequestMapping("/venue")
public class VenueController 
{
	@Autowired
	private VenueService venueService;
	
//	Inserting A Venue Into The Database
	@PostMapping
	public ResponseEntity<ResponseStructure<Venue>> addVenue(@RequestBody Venue venue)
	{
		return venueService.addVenue(venue);
	}
	
//	Fetch All The Venue Records
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue()
	{
		return venueService.getAllVenue();
	}
	
//	Fetch The Venue Record By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Venue>> getVenueById(@PathVariable Integer id)
	{
		return venueService.getVenueById(id);
	}
	
//	Update The Venue In The Database
	@PutMapping
	public ResponseEntity<ResponseStructure<Venue>> updateVenue(@RequestBody Venue venue)
	{
		return venueService.updateVenue(venue);
	}
	
//	Delete The Venue From The Database Using Id
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Venue>> deleteVenue(@PathVariable Integer id)
	{
		return venueService.deleteVenue(id);
	}
	
//	Fetch The Event By The Venue Id
	@GetMapping("/venueId/{id}")
	public ResponseEntity<ResponseStructure<List<Event>>> getEventsByVenueId(@PathVariable Integer id)
	{ 
		return venueService.getEventsByVenueId(id);
	}

	
//	Fetch The Venue By The Venue Location
	@GetMapping("/location/{location}")
	public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByVenueLocation(@PathVariable String location)
	{ 
		return venueService.getVenueByVenueLocation(location);
	}

	
//	Pagination And Sorting
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Venue>>> getEventByPaginationAndSorting(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field)
	{
		return venueService.getEventByPaginationAndSorting(pageNumber, pageSize, field);
	}
}
