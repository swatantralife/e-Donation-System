package com.swatantra.donation.exceptions.model;

import java.util.Objects;

public class ExceptionMessage {
	
	private String message;
	private Long timeStamp;
	private Integer status;
	
	
	public ExceptionMessage(String message, Long timeStamp, Integer status) {
		super();
		this.message = message;
		this.timeStamp = timeStamp;
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Long getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		return Objects.hash(message, status, timeStamp);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionMessage other = (ExceptionMessage) obj;
		return Objects.equals(message, other.message) && Objects.equals(status, other.status)
				&& Objects.equals(timeStamp, other.timeStamp);
	}
	
	
	

}
