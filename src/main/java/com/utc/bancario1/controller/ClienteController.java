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

    @GetMapping
    public String index(Model model) {
        model.addAttribute("listaClientes", service.listar());
        return "clientes/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/agregar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", service.buscarPorId(id));
        return "clientes/editar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente c, RedirectAttributes ra) {
        if(c.getId() == null){
            service.guardar(c);
            ra.addFlashAttribute("mensaje","Successfully registered customer");
            ra.addFlashAttribute("tipo","success");
        } else {
            service.guardar(c);
            ra.addFlashAttribute("mensaje","Successfully updated client");
            ra.addFlashAttribute("tipo","info");
        }
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra){
        Cliente c = service.buscarPorId(id);
        if(c.getCuentas() != null && !c.getCuentas().isEmpty()){
            ra.addFlashAttribute("mensaje","This customer cannot be deleted because it has associated accounts");
            ra.addFlashAttribute("tipo","warning");
            return "redirect:/clientes";
        }
        service.eliminar(id);
        ra.addFlashAttribute("mensaje","Cliente eliminado correctamente");
        ra.addFlashAttribute("tipo","success");
        return "redirect:/clientes";
    }
}
