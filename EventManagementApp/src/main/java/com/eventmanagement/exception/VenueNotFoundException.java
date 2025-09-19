package com.eventmanagement.exception;

public class VenueNotFoundException extends RuntimeException 
{
	public VenueNotFoundException(String message)
	{
		super(message);
	}
}
