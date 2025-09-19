package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventmanagement.entity.Registration;
import com.eventmanagement.repository.RegistrationRepository;

@Repository
public class RegistrationDao 
{
	@Autowired
	private RegistrationRepository registrationRepository;
	
	public Registration createRegistration(Registration registration)
	{
		return registrationRepository.save(registration);
	}
	
	public List<Registration> getAllRegistration()
	{
		return registrationRepository.findAll();
	}
	
	public Optional<Registration> getRegistrationById(Integer id)
	{
		return registrationRepository.findById(id);
	}
	
	public void cancelRegistration(Registration registration)
	{
		registrationRepository.delete(registration);
	}
	
	public List<Registration> getRegistrationByEventId(Integer id)
	{
		return registrationRepository.getRegistrationByEventId(id);
	}
	
	public List<Registration> getRegistrationByAttendee(Integer id)
	{
		return registrationRepository.getRegistrationByAttendee(id);
	}
	
	
}
