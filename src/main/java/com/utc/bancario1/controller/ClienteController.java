package com.utc.bancario1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.utc.bancario1.entity.Cliente;
import com.utc.bancario1.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("listaClientes", service.listar());
        return "cliente";
    }

    // GUARDAR / ACTUALIZAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente c) {
        service.guardar(c); // si tiene ID = actualiza
        return "redirect:/clientes";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente c = service.buscarPorId(id);
        model.addAttribute("cliente", c);
        model.addAttribute("listaClientes", service.listar());
        return "cliente";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/clientes";
    }
}
