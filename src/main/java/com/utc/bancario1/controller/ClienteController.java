package com.utc.bancario1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.utc.bancario1.entity.Cliente;
import com.utc.bancario1.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    // INDEX
    @GetMapping
    public String index(Model model) {
        model.addAttribute("listaClientes", service.listar());
        return "clientes/index";
    }

    // FORM NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/agregar";
    }

    // FORM EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", service.buscarPorId(id));
        return "clientes/editar";
    }

    // GUARDAR (nuevo y editar)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente c, RedirectAttributes ra) {

        if (c.getId() == null) {
            service.guardar(c);
            ra.addFlashAttribute("mensaje", "Cliente registrado correctamente");
            ra.addFlashAttribute("tipo", "success");
        } else {
            service.guardar(c);
            ra.addFlashAttribute("mensaje", "Cliente actualizado correctamente");
            ra.addFlashAttribute("tipo", "info");
        }

        return "redirect:/clientes";
    }



    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        service.eliminar(id);
        ra.addFlashAttribute("mensaje", "Cliente eliminado correctamente");
        ra.addFlashAttribute("tipo", "success");
        return "redirect:/clientes";
    }
}
