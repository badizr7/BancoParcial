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
public class ControladorBanco {
    @Autowired
    private ServicioBanco servicioBanco;

    @GetMapping("/usuario/saldo")
    public String mostrarSaldo(Model modelo, Principal principal) {
        Cuenta cuenta = servicioBanco.obtenerCuentaPorTitular(principal.getName());
        modelo.addAttribute("saldoDisponible", cuenta.getSaldoDisponible());
        return "saldo";
    }

    @GetMapping("/admin/cuentas")
    public String listarCuentas(Model modelo) {
        modelo.addAttribute("listaCuentas", servicioBanco.obtenerTodasLasCuentas());
        return "listaCuentas";
    }

    @PostMapping("/usuario/transferir")
    public String realizarTransferencia(@RequestParam String cuentaOrigen, @RequestParam String cuentaDestino, @RequestParam double monto) {
        servicioBanco.realizarTransferencia(cuentaOrigen, cuentaDestino, monto);
        return "redirect:/usuario/saldo";
    }
}