package com.utc.bancario1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.utc.bancario1.entity.Cuenta;
import com.utc.bancario1.entity.Cliente;
import com.utc.bancario1.service.CuentaService;
import com.utc.bancario1.service.ClienteService;
import java.util.List;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("listaCuentas", cuentaService.listar());
        return "cuentas/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("clientes", clienteService.listar()); // Para el select
        return "cuentas/agregar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cuenta", cuentaService.buscarPorId(id));
        model.addAttribute("clientes", clienteService.listar());
        return "cuentas/editar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cuenta cuenta, RedirectAttributes ra) {
        cuentaService.guardar(cuenta);
        ra.addFlashAttribute("mensaje", "Cuenta guardada correctamente");
        ra.addFlashAttribute("tipo", "success");
        return "redirect:/cuentas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        Cuenta c = cuentaService.buscarPorId(id);
        if (c != null) {
            cuentaService.eliminar(id);
            ra.addFlashAttribute("mensaje", "Cuenta eliminada correctamente");
            ra.addFlashAttribute("tipo", "success");
        } else {
            ra.addFlashAttribute("mensaje", "No se puede eliminar, está relacionada");
            ra.addFlashAttribute("tipo", "error");
        }
        return "redirect:/cuentas";
    }
}
