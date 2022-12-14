package com.alianza.prueba.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alianza.prueba.dto.ClientDto;

@Entity
public class Client {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String sharedKey;
	private String email;
	private String phone;
	private LocalDate creationDate;
	
	public Client() {
	}
	
	public Client(String name, String sharedKey, String email, String phone, LocalDate creationDate) {
		this.name = name;
		this.sharedKey = sharedKey;
		this.email = email;
		this.phone = phone;
		this.creationDate = creationDate;
	}
	
	public Client(ClientDto clientDto) {
		this.name = clientDto.getName();
		this.sharedKey = clientDto.getSharedKey();
		this.email = clientDto.getEmail();
		this.phone = clientDto.getPhone();
		this.creationDate = clientDto.getCreationDate();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
