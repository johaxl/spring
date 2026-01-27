package com.utc.bancario1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utc.bancario1.entity.Cuenta;
import com.utc.bancario1.repository.CuentaRepository;
import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository repo;

    public List<Cuenta> listar() { return repo.findAll(); }

    public Cuenta buscarPorId(Long id) { return repo.findById(id).orElse(null); }

    public Cuenta guardar(Cuenta c) { return repo.save(c); }

    public void eliminar(Long id) { repo.deleteById(id); }
}
