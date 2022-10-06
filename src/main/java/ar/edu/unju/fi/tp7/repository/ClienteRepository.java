package ar.edu.unju.fi.tp7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.fi.tp7.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findByDni(String dni);
}
