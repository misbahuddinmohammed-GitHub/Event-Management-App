package com.eventmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Registration;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer> 
{
	@Query("select a.registrations from Attendee a where a.id=?1")
	List<Registration> getRegistrationByAttendee(Integer id);
	
	@Query("select a from Attendee a where a.contact=?1")
	Optional<Attendee> getAttendeeByContact(Long contact);
}
