package com.eventmanagement.exception;

public class OrganizerNotFoundException extends RuntimeException 
{
	public OrganizerNotFoundException(String message)
	{
		super(message);
	}
}
