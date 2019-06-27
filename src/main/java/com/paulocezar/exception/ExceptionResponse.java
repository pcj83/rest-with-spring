package com.paulocezar.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String mesage;
	private String details;
	
	public ExceptionResponse(Date timestamp, String mesage, String details) {
		super();
		this.timestamp = timestamp;
		this.mesage = mesage;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMesage() {
		return mesage;
	}

	public String getDetails() {
		return details;
	}
}
