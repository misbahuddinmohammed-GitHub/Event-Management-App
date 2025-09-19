package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.entity.Organizer;
import com.eventmanagement.repository.OrganizerRepository;

@Repository
public class OrganizerDao 
{
	@Autowired
	private OrganizerRepository organizerRepository;
	
	public Organizer addOrganizer(Organizer organizer)
	{
		return organizerRepository.save(organizer);
	}
	
	public List<Organizer> getAllOrganizer()
	{
		return organizerRepository.findAll();
	}
	
	public Optional<Organizer> getOrganizerById(Integer id)
	{
		return organizerRepository.findById(id);
	}
	
	public Organizer updateOrganizer(Organizer organizer)
	{
		return organizerRepository.save(organizer);
	}
	
	public void deleteOrganizer(Organizer organizer)
	{
		organizerRepository.delete(organizer);
	}
	
	public Page<Organizer> getOrganizerByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field)
	{
		return organizerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
	}
}
