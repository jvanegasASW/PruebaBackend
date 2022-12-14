package com.alianza.prueba.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alianza.prueba.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer>{

	Optional<Client> findBySharedKey(String sharedKey);
	Optional<Client> findByEmail(String email);
}
