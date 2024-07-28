package me.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.model.Client;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {

	boolean existsByEmail(String email);

}
