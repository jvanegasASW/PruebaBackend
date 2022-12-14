package com.alianza.prueba.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alianza.prueba.dto.ClientDto;
import com.alianza.prueba.exception.AlianzaException;
import com.alianza.prueba.service.ClientService;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins="http://localhost:4200/")
public class ClientController {

	private final ClientService clientService;
	Logger logger = Logger.getLogger(ClientController.class.getName());

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping
	public List<ClientDto> all(){
		
		return clientService.findAll();
	}
	
	@GetMapping("/{sharedKey}")
	public ResponseEntity<?> findBySharedkey(@PathVariable String sharedKey) {
		
		try {
			return new ResponseEntity<>(clientService.findBySharedKey(sharedKey), HttpStatus.OK);
		} catch (AlianzaException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ClientDto clientDto){
		
		try {
			return new ResponseEntity<>(clientService.create(clientDto), HttpStatus.OK);
		} catch (AlianzaException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
		}
	}
	
	@PostMapping("/export")
	public ResponseEntity<?> exportClientsInCsv(@RequestBody List<ClientDto> clients) {

		try {
			String filename = "clients.csv";
			InputStreamResource file = new InputStreamResource(clientService.export(clients));
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
					.contentType(MediaType.parseMediaType("application/csv")).body(file);
		} catch (AlianzaException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
		}

	}
}
