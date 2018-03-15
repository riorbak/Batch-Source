package com.revature.ERS.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursement implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String author;
	private int authID;
	private int id;
	private int amount; 
	private String description;
	private Blob receipt;
	private String status;
	private int statusID;
	private String type;
	private int typeID;
	private Timestamp submitted;
	private Timestamp resolved;
	
	
	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public Reimbursement()
	{
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Blob getReceipt() {
		return receipt;
	}
	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getAuthID() {
		return authID;
	}

	public void setAuthID(int authID) {
		this.authID = authID;
	}
	
	
	
}
