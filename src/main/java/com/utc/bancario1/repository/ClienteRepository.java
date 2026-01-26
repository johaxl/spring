package com.utc.bancario1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utc.bancario1.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
