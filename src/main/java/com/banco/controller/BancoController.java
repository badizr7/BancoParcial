package com.banco.controller;

import com.banco.model.Cuenta;
import com.banco.services.ServicioBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class BancoController {
    @Autowired
    private ServicioBanco servicioBanco;

    @GetMapping("/usuario/saldo")
    public String mostrarSaldo(Model modelo, Principal principal) {
        Cuenta cuenta = servicioBanco.obtenerCuentaPorTitular(principal.getName());
        if (cuenta == null) {
            return "redirect:/error"; // Redirigir a la página de error si no se encuentra la cuenta
        }
        modelo.addAttribute("saldoDisponible", cuenta.getSaldoDisponible());
        return "saldo"; // Debe existir el archivo saldo.html
    }

    @GetMapping("/admin/cuentas")
    public String listarCuentas(Model modelo) {
        modelo.addAttribute("listaCuentas", servicioBanco.obtenerTodasLasCuentas());
        return "listaCuentas"; // Debe existir el archivo listaCuentas.html
    }

    @PostMapping("/usuario/transferir")
    public String realizarTransferencia(@RequestParam String cuentaOrigen, @RequestParam String cuentaDestino, @RequestParam double monto) {
        servicioBanco.realizarTransferencia(cuentaOrigen, cuentaDestino, monto);
        return "redirect:/usuario/saldo";
    }
}
