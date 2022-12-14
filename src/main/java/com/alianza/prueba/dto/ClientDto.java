package com.alianza.prueba.dto;

import java.time.LocalDate;

import com.alianza.prueba.entity.Client;

public class ClientDto {
	
	private String name;
	private String sharedKey;
	private String email;
	private String phone;
	private LocalDate creationDate;
	
	public ClientDto() {
	}
	
	public ClientDto(Client client) {
		this.name = client.getName();
		this.sharedKey = client.getSharedKey();
		this.email = client.getEmail();
		this.phone = client.getPhone();
		this.creationDate = client.getCreationDate();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the sharedKey
	 */
	public String getSharedKey() {
		return sharedKey;
	}
	
	/**
	 * @param sharedKey the sharedKey to set
	 */
	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * @return the creationDate
	 */
	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
