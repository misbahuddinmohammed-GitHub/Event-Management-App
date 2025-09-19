package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.OrganizerDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.exception.OrganizerNotFoundException;

@Service
public class OrganizerService 
{
	@Autowired
	private OrganizerDao organizerDao;
	
	public ResponseEntity<ResponseStructure<Organizer>> addOrganizer(Organizer organizer)
	{
		ResponseStructure<Organizer> structure = new ResponseStructure<Organizer>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Organizer Record Is Inserted Into The Database");
		structure.setData(organizerDao.addOrganizer(organizer));
		
		return new ResponseEntity<ResponseStructure<Organizer>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer()
	{
		ResponseStructure<List<Organizer>> structure = new ResponseStructure<List<Organizer>>();
		
		List<Organizer> organizers = organizerDao.getAllOrganizer();
		if(!organizers.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Organizer Records Are Retrieved From The Database");
			structure.setData(organizers);
			
			return new ResponseEntity<ResponseStructure<List<Organizer>>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new OrganizerNotFoundException("No Organizers Found In The Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<Organizer>> getOrganizerById(Integer id)
	{
		ResponseStructure<Organizer> structure = new ResponseStructure<Organizer>();
		
		Optional<Organizer> optional = organizerDao.getOrganizerById(id);
		
		if(optional.isPresent())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Organizer Record Is Retrieved From The Database By The Organizer Id");
			structure.setData(optional.get());
			
			return new ResponseEntity<ResponseStructure<Organizer>>(HttpStatus.FOUND);
		}
		else
		{
			throw new OrganizerNotFoundException("Organizer Record Not Found By This Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(Organizer organizer)
	{
		ResponseStructure<Organizer> structure = new ResponseStructure<Organizer>();
		
		structure.setStatusCode(HttpStatus.FOUND.value());
		structure.setMessage("Organizer Record Is Updated");
		structure.setData(organizerDao.updateOrganizer(organizer));
		
		return new ResponseEntity<ResponseStructure<Organizer>>(structure, HttpStatus.FOUND);
	}
	
	public ResponseEntity<ResponseStructure<Organizer>> deleteOrganizer(Integer id)
	{
		ResponseStructure<Organizer> structure = new ResponseStructure<Organizer>();
		
		Optional<Organizer> optional = organizerDao.getOrganizerById(id);
		
		if(optional.isPresent())
		{
			organizerDao.deleteOrganizer(optional.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Organizer Record Is Deleted From The Database");
			
			return new ResponseEntity<ResponseStructure<Organizer>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new OrganizerNotFoundException("Organizer Record Cannot Be Deleted From The Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizerByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field)
	{
		Page<Organizer> pages = organizerDao.getOrganizerByPaginationAndSorting(pageNumber, pageSize, field);
		
		ResponseStructure<Page<Organizer>> structure = new ResponseStructure<Page<Organizer>>();
		
		if(!pages.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Organizer Record Retrieved");
			structure.setData(pages);
			
			return new ResponseEntity<ResponseStructure<Page<Organizer>>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new OrganizerNotFoundException("Organizer Record Is Not Found In The Database");
		}
	}
}
