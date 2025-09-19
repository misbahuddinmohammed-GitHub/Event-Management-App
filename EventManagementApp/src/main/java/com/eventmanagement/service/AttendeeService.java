package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.AttendeeDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.exception.AttendeeNotFoundException;
import com.eventmanagement.exception.RegistrationNotFoundException;

@Service
public class AttendeeService 
{
	@Autowired
	private AttendeeDao attendeeDao;
	
	public ResponseEntity<ResponseStructure<Attendee>> registerAttendee(Attendee attendee)
	{
		ResponseStructure<Attendee> structure = new ResponseStructure<Attendee>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Attendee Record Is Inserted Into The Database");
		structure.setData(attendeeDao.registerAttendee(attendee));
		
		return new ResponseEntity<ResponseStructure<Attendee>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttendee()
	{
		ResponseStructure<List<Attendee>> structure = new ResponseStructure<List<Attendee>>();
		
		List<Attendee> attendees = attendeeDao.getAllAttendee();
		if(!attendees.isEmpty())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Attendee Records Are Retrieved From The Database");
			structure.setData(attendees);
			
			return new ResponseEntity<ResponseStructure<List<Attendee>>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new AttendeeNotFoundException("No Attendees Found In The Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeeById(Integer id)
	{
		ResponseStructure<Attendee> structure = new ResponseStructure<Attendee>();
		
		Optional<Attendee> optional = attendeeDao.getAttendeeById(id);
		
		if(optional.isPresent())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Attendee Record Is Retrieved From The Database By The Attendee Id");
			structure.setData(optional.get());
			
			return new ResponseEntity<ResponseStructure<Attendee>>(HttpStatus.FOUND);
		}
		else
		{
			throw new AttendeeNotFoundException("Attendee Record Not Found By This Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(Attendee attendee)
	{
		ResponseStructure<Attendee> structure = new ResponseStructure<Attendee>();
		
		structure.setStatusCode(HttpStatus.FOUND.value());
		structure.setMessage("Attendee Record Is Updated");
		structure.setData(attendeeDao.updateAttendee(attendee));
		
		return new ResponseEntity<ResponseStructure<Attendee>>(structure, HttpStatus.FOUND);
	}
	
	
	public ResponseEntity<ResponseStructure<Attendee>> deleteAttendee(Integer id)
	{
		ResponseStructure<Attendee> structure = new ResponseStructure<Attendee>();
		
		Optional<Attendee> optional = attendeeDao.getAttendeeById(id);
		
		if(optional.isPresent())
		{
			attendeeDao.deleteAttendee(optional.get());;
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Attendee Record Is Deleted From The Database");
			
			return new ResponseEntity<ResponseStructure<Attendee>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new AttendeeNotFoundException("Attendee Record Cannot Be Deleted From The Database");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendee(Integer id)
	{
		ResponseStructure<List<Registration>> structure = new ResponseStructure<List<Registration>>();
		
		List<Registration> registrations = attendeeDao.getRegistrationByAttendee(id);
		
		if(!registrations.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Registration Retrieved By Attendee");
			structure.setData(registrations);
			
			return new ResponseEntity<ResponseStructure<List<Registration>>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new RegistrationNotFoundException("Registration Not Found By This Attendee");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeeByContact(Long contact)
	{
		ResponseStructure<Attendee> structure = new ResponseStructure<Attendee>();
		
		Optional<Attendee> optional = attendeeDao.getAttendeeByContact(contact);
		
		if(optional.isPresent())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Attendee Found By This Contact");
			structure.setData(optional.get());
			
			return new ResponseEntity<ResponseStructure<Attendee>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new AttendeeNotFoundException("Attendee Not Found By This Contact");
		}
	}
	
	public ResponseEntity<ResponseStructure<Page<Attendee>>> getAttendeeByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field)
	{
		Page<Attendee> pages = attendeeDao.getAttendeeByPaginationAndSorting(pageNumber, pageSize, field);
		
		ResponseStructure<Page<Attendee>> structure = new ResponseStructure<Page<Attendee>>();
		
		if(!pages.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Attendee Record Retrieved");
			structure.setData(pages);
			
			return new ResponseEntity<ResponseStructure<Page<Attendee>>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new AttendeeNotFoundException("Attendee Record Is Not Found In The Database");
		}
	}
}
