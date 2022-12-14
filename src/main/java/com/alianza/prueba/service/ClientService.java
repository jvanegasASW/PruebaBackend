package com.alianza.prueba.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.alianza.prueba.dto.ClientDto;
import com.alianza.prueba.exception.AlianzaException;

public interface ClientService {
	
	List<ClientDto> findAll();
	ClientDto findBySharedKey(String sharedKey) throws AlianzaException;
	ClientDto create(ClientDto clientDto) throws AlianzaException;
	ByteArrayInputStream export(List<ClientDto> clients) throws AlianzaException;
}
