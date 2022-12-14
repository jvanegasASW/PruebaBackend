package com.alianza.prueba;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.alianza.prueba.dto.ClientDto;
import com.alianza.prueba.entity.Client;
import com.alianza.prueba.exception.AlianzaException;
import com.alianza.prueba.repository.ClientRepository;
import com.alianza.prueba.service.ClientServiceImpl;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ClientServiceImpl.class}, loader=AnnotationConfigContextLoader.class )
class ClientServiceImplTest {

	@Autowired
	private ClientServiceImpl clientServiceImpl;
	
	@MockBean
	private  ClientRepository clientRepository;
	
	public static final Client client = new Client();
	public static final Optional<Client> optional = Optional.of(client);
	
	@BeforeAll
	static void setUp() {
		client.setName("Janer Vanegas");
		client.setSharedKey("jvanegas");
		client.setEmail("jvanegas@mail.com");
		client.setPhone("311111");
	}
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(clientServiceImpl);
		Assertions.assertNotNull(clientRepository);
	}
	
	@Test
	void findBySharedKey() throws AlianzaException{
		
		when(clientRepository.findBySharedKey(any(String.class))).thenReturn(optional);
	
		ClientDto mockclient = clientServiceImpl.findBySharedKey("jvanegas");
	
		Assertions.assertNotNull(client);
		Assertions.assertEquals(client.getSharedKey(), mockclient.getSharedKey());
	}

}
