package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.RegistrationDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.exception.RegistrationNotFoundException;
import com.eventmanagement.repository.AttendeeRepository;
import com.eventmanagement.repository.EventRepository;
@Service
public class RegistrationService 
{
	@Autowired
	private RegistrationDao registrationDao;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private AttendeeRepository attendeeRepository;
	
	public ResponseEntity<ResponseStructure<Registration>> createRegistration(Registration registration)
	{
		Optional<Event> optional1 = eventRepository.findById(registration.getEvent().getId());
		Optional<Attendee> optional2 = attendeeRepository.findById(registration.getAttendee().getId());
		
		if(optional1.isPresent())
		{
			registration.setEvent(optional1.get());
		}
		if(optional2.isPresent())
		{
			registration.setAttendee(optional2.get());
		}
		
		ResponseStructure<Registration> structure = new ResponseStructure<Registration>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Registration Record Is Inserted Into The Database");
		structure.setData(registrationDao.createRegistration(registration));
		
		return new ResponseEntity<ResponseStructure<Registration>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistration()
	{
		ResponseStructure<List<Registration>> structure = new ResponseStructure<List<Registration>>();
		
		List<Registration> registrations = registrationDao.getAllRegistration();
		if(!registrations.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Registration Records Are Retrieved From The Database");
			structure.setData(registrations);
			
			return new ResponseEntity<ResponseStructure<List<Registration>>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new RegistrationNotFoundException("No Registrations Found In The Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<Registration>> getRegistrationById(Integer id)
	{
		ResponseStructure<Registration> structure = new ResponseStructure<Registration>();
		
		Optional<Registration> optional = registrationDao.getRegistrationById(id);
		
		if(optional.isPresent())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Registration Record Is Retrieved From The Database By The Registration Id");
			structure.setData(optional.get());
			
			return new ResponseEntity<ResponseStructure<Registration>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new RegistrationNotFoundException("Registration Record Not Found By This Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Registration>> cancelRegistration(Integer id)
	{
		ResponseStructure<Registration> structure = new ResponseStructure<Registration>();
		
		Optional<Registration> optional = registrationDao.getRegistrationById(id);
		
		if(optional.isPresent())
		{
			registrationDao.cancelRegistration(optional.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Registration Record Is Deleted From The Database");
			
			return new ResponseEntity<ResponseStructure<Registration>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new RegistrationNotFoundException("Registration Record Cannot Be Deleted From The Database");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByEventId(Integer id)
	{
		ResponseStructure<List<Registration>>  structure = new ResponseStructure<List<Registration>>();
		
		List<Registration> registrations = registrationDao.getRegistrationByEventId(id);
		if(!registrations.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Registration Found By This Event Id");
			structure.setData(registrations);
			
			return new ResponseEntity<ResponseStructure<List<Registration>>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new RegistrationNotFoundException("Registration Not Found By This Event Id");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendee(Integer id)
	{
		ResponseStructure<List<Registration>>  structure = new ResponseStructure<List<Registration>>();
		
		List<Registration> registrations = registrationDao.getRegistrationByAttendee(id);
		if(!registrations.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Registration Found By This Attendee Id");
			structure.setData(registrations);
			
			return new ResponseEntity<ResponseStructure<List<Registration>>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new RegistrationNotFoundException("Registration Not Found By This Attendee Id");
		}
	}
	
	
	
	
}
