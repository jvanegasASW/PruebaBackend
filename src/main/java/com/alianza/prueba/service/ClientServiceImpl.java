package com.alianza.prueba.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alianza.prueba.dto.ClientDto;
import com.alianza.prueba.entity.Client;
import com.alianza.prueba.exception.AlianzaException;
import com.alianza.prueba.repository.ClientRepository;
import com.alianza.prueba.utils.UtilConstants;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository; 
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<ClientDto> findAll() {
		
		return createListClientDto(clientRepository.findAll());
	}

	@Override
	public ClientDto findBySharedKey(String sharedKey) throws AlianzaException{
		
		Optional<Client> optional = clientRepository.findBySharedKey(sharedKey);
		if(optional.isPresent()) {
			return new ClientDto(optional.get());
		} else {
			throw new AlianzaException(UtilConstants.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ClientDto create(ClientDto clientDto) throws AlianzaException{

		Optional<Client> optional = clientRepository.findByEmail(clientDto.getEmail());
		if(!optional.isPresent()) {
			clientDto.setCreationDate(LocalDate.now());
			clientDto.setSharedKey(clientDto.getEmail().split("@")[0]);
			Client client = clientRepository.save(new Client(clientDto));
			return new ClientDto(client);
		} else {
			
			throw new AlianzaException(UtilConstants.CLIENT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@Override
	public ByteArrayInputStream export(List<ClientDto> clients) throws AlianzaException {
		
		return writeClientToCsv(clients);
	}
	
	private List<ClientDto> createListClientDto(Iterable<Client> clients){
		
		List<ClientDto> listClientDto = new ArrayList<>();
		for (Client client : clients) {
			listClientDto.add(new ClientDto(client));
		}
		return listClientDto;
	}
	
	private ByteArrayInputStream writeClientToCsv(List<ClientDto> clients) throws AlianzaException {
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL));
			for (ClientDto clientDto : clients) {
				csvPrinter.printRecord(clientDto.getSharedKey(), clientDto.getName(), clientDto.getEmail(), clientDto.getPhone(), clientDto.getCreationDate());
			}
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new AlianzaException(UtilConstants.ERROR_CREATING_FILE,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

}
