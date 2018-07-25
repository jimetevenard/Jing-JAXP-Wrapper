package com.jimetevenard.utils;

import java.util.Optional;


public class OperationResult {
	
	private Status status;
	private Exception error;
	
	public OperationResult(){
		reset();
	}
	
	public void reset(){
		this.status = Status.UNTRIGGERED;
		this.error = null;
	}
	
	public void succes(){
		this.status = Status.SUCCES;
	}
	
	public void fail(){
		fail(null);
	}
	
	public void fail(Exception cause){
		this.status = Status.FAILED;
		this.error = cause;
	}
	

	public boolean isSucces(){
		return status.equals(Status.SUCCES);
	}
	
	public Status getStatus() {
		return status;
	}

	
	public Optional<Exception> getError() {
		return Optional.ofNullable(this.error);
	}
	



	private enum Status {
		UNTRIGGERED, FAILED, SUCCES
	}

}
