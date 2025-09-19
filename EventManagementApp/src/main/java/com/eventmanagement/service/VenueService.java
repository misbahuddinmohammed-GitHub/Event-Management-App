package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.VenueDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.exception.EventNotFoundException;
import com.eventmanagement.exception.VenueNotFoundException;

@Service
public class VenueService 
{
	@Autowired
	private VenueDao venueDao;
	
	public ResponseEntity<ResponseStructure<Venue>> addVenue(Venue venue)
	{
		ResponseStructure<Venue> structure = new ResponseStructure<Venue>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Venue Record Is Inserted Into The Database");
		structure.setData(venueDao.addVenue(venue));
		
		return new ResponseEntity<ResponseStructure<Venue>>(structure,HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue()
	{
		ResponseStructure<List<Venue>> structure = new ResponseStructure<List<Venue>>();
		
		List<Venue> venues = venueDao.getAllVenue();
		
		if(!venues.isEmpty())
		{	
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Venue Records Are Retrieved From The Database");
			structure.setData(venues);
			
			return new ResponseEntity<ResponseStructure<List<Venue>>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new VenueNotFoundException("No Venues Found In The Database");
		}
	}
		
		
	public ResponseEntity<ResponseStructure<Venue>> getVenueById(Integer id)
	{
		ResponseStructure<Venue> structure = new ResponseStructure<Venue>();
		
		Optional<Venue> optional = venueDao.getVenueById(id);
		
		if(optional.isPresent())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Venue Record Is Retrieved From The Database By Venue Id");
			structure.setData(optional.get());
			
			return new ResponseEntity<ResponseStructure<Venue>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new VenueNotFoundException("Venue Record Not Found By This Id");
		}
	}
		
	public ResponseEntity<ResponseStructure<Venue>> updateVenue(Venue venue)
	{
		ResponseStructure<Venue> structure = new ResponseStructure<Venue>();
		
		structure.setStatusCode(HttpStatus.FOUND.value());
		structure.setMessage("Venue Record Is Updated");
		structure.setData(venueDao.updateVenue(venue));
		
		return new ResponseEntity<ResponseStructure<Venue>>(structure, HttpStatus.FOUND);
	}
	
	
	public ResponseEntity<ResponseStructure<Venue>> deleteVenue(Integer id)
	{
		ResponseStructure<Venue> structure = new ResponseStructure<Venue>();
		
		Optional<Venue> optional = venueDao.getVenueById(id);
		if(optional.isPresent())
		{
			venueDao.deleteVenue(optional.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Venue Record Is Deleted From The Database");
			
			return new ResponseEntity<ResponseStructure<Venue>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new VenueNotFoundException("Venue Record Cannot Be Deleted");
		}
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<Event>>> getEventsByVenueId(Integer id)
	{
		ResponseStructure<List<Event>> structure = new ResponseStructure<List<Event>>();
		
		List<Event> events = venueDao.getEventsByVenueId(id);
		
		if(!events.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Events Retrieved By This Venue Id");
			structure.setData(events);
			
			return new ResponseEntity<ResponseStructure<List<Event>>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new EventNotFoundException("Events Not Retrieved By This Venue Id");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByVenueLocation(String location)
	{
		ResponseStructure<List<Venue>> structure = new ResponseStructure<List<Venue>>();
		
		List<Venue> venues = venueDao.getVenueByLocation(location);
		
		if(!venues.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Venue Retrieved By This Venue Location");
			structure.setData(venues);
			
			return new ResponseEntity<ResponseStructure<List<Venue>>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new VenueNotFoundException("Venue Not Retrieved By This Venue Location");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Page<Venue>>> getEventByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field)
	{
		Page<Venue> pages = venueDao.getVenueByPaginationAndSorting(pageNumber, pageSize, field);
		ResponseStructure<Page<Venue>> structure = new ResponseStructure<Page<Venue>>();
		
		if(!pages.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Venue Record Retrieved");
			structure.setData(pages);
			
			return new ResponseEntity<ResponseStructure<Page<Venue>>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new VenueNotFoundException("Venue Record Is Not Found In The Database");
		}
	}
}
