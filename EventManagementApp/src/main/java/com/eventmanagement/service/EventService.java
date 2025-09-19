package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.EventDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.exception.EventNotFoundException;
import com.eventmanagement.repository.OrganizerRepository;
import com.eventmanagement.repository.VenueRepository;



@Service
public class EventService 
{
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private VenueRepository venueRepository;
	
	@Autowired
	private OrganizerRepository organizerRepository;
	
	public ResponseEntity<ResponseStructure<Event>> createEvent(Event event)
	{
		Optional<Organizer> optional1 = organizerRepository.findById(event.getOrganizer().getId());
		Optional<Venue> optional2 = venueRepository.findById(event.getVenue().getId());
		
		if(optional1.isPresent())
		{
			event.setOrganizer(optional1.get());
		}
		if(optional2.isPresent())
		{
			event.setVenue(optional2.get());
		}
		
		ResponseStructure<Event> structure = new ResponseStructure<Event>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Event Is Recorded");
		structure.setData(eventDao.createEvent(event));
		
		return new ResponseEntity<ResponseStructure<Event>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent()
	{
		ResponseStructure<List<Event>> structure = new ResponseStructure<List<Event>>();
		
		List<Event> events = eventDao.getAllEvent();
		if(!events.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("All The Records of The Event Are Retrieved");
			structure.setData(events);
			
			return new ResponseEntity<ResponseStructure<List<Event>>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new EventNotFoundException("No Events Found In The Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<Event>> getEventById(Integer id)
	{
		ResponseStructure<Event> structure = new ResponseStructure<Event>();
		
		Optional<Event> optional = eventDao.getEventById(id);
		
		if(optional.isPresent())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Event Record Is Retrieved By The Event Id");
			structure.setData(optional.get());
			
			return new ResponseEntity<ResponseStructure<Event>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new EventNotFoundException("Event record Not Found By This Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Event>> updateEvent(Event event)
	{
		ResponseStructure<Event> structure = new ResponseStructure<Event>();
		
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Event Record Is Updated");
		structure.setData(eventDao.updateEvent(event));
		
		return new ResponseEntity<ResponseStructure<Event>>(structure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Event>> deleteEvent(Integer id)
	{
		ResponseStructure<Event> structure = new ResponseStructure<Event>();
		
		Optional<Event> optional = eventDao.getEventById(id);
		
		if(optional.isPresent())
		{
			eventDao.deleteEvent(optional.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Event Record Is Deleted From The Database");
			
			return new ResponseEntity<ResponseStructure<Event>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new EventNotFoundException("Event Record cannot Be Deleted");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeeByEventId(Integer id)
	{
		ResponseStructure<Attendee> structure = new ResponseStructure<Attendee>();
		
		Optional<Attendee> optional = eventDao.getAttendeeByEventId(id);
		
		if(optional.isPresent())
		{
			structure.setData(optional.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Attendee Record is Retrieved for the Event Id");
			
			return new ResponseEntity<ResponseStructure<Attendee>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new EventNotFoundException("Invalid Event Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Event>>> getEventByOrganizerId(Integer id)
	{
		ResponseStructure<List<Event>> structure = new ResponseStructure<List<Event>>();
		
		List<Event> events = eventDao.getEventByOrganizerId(id);
		
		if(!events.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Event Record Retreived By Organizer Id");
			structure.setData(events);
			
			return new ResponseEntity<ResponseStructure<List<Event>>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new EventNotFoundException("No Organizers Are Found In This Event");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Page<Event>>> getEventByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field)
	{
		Page<Event> pages = eventDao.getEventByPaginationAndSorting(pageNumber, pageSize, field);
		ResponseStructure<Page<Event>> structure = new ResponseStructure<Page<Event>>();
		
		if(!pages.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Event Record Retrieved");
			structure.setData(pages);
			
			return new ResponseEntity<ResponseStructure<Page<Event>>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new EventNotFoundException("Event Record Is Not Found In The Database");
		}
	}
}
