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
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.service.OrganizerService;

@RestController
@RequestMapping("/organizer")
public class OrganizerController 
{
	@Autowired
	private OrganizerService organizerService;
	
//	Inserting A Organizer Into The Database
	@PostMapping
	public ResponseEntity<ResponseStructure<Organizer>> addOrganizer(@RequestBody Organizer organizer)
	{
		return organizerService.addOrganizer(organizer);
	}
	
	
//	Fetch All The Organizer Records
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer()
	{
		return organizerService.getAllOrganizer();
	}
	
	
//	Fetch The Organizer Record By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Organizer>> getOrganizerById(@PathVariable Integer id)
	{
		return organizerService.getOrganizerById(id);
	}
	
	
//	Update The Organizer In The Database
	@PutMapping
	public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(@RequestBody Organizer organizer)
	{
		return organizerService.updateOrganizer(organizer);
	}
	
	
//	Delete The Organizer From The Database Using Id
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Organizer>> deleteOrganizer(@PathVariable Integer id)
	{
		return organizerService.deleteOrganizer(id);
	}
	
	
//	Pagination And Sorting
	@GetMapping("/page/{pageNumber}/{pageSize}{field}")
	public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizerByPaginationAndSorting(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field)
	{
		return organizerService.getOrganizerByPaginationAndSorting(pageNumber, pageSize, field);
	}
}
