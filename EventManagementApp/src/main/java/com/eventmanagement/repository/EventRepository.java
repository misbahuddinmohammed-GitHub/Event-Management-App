package com.eventmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

	@Query("select r.attendee from Registration r where r.event.id=?1")
	Optional<Attendee> getAttendeeByEventId(Integer id);

	@Query("select e from Event e where e.organizer.id=?1")
	List<Event> getEventByOrganizerId(Integer id);
}
