package com.banco.controller;

import com.banco.services.ServicioBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private ServicioBanco servicioBanco;

    // Mostrar formulario de transferencia
    @GetMapping("/transferir")
    public String mostrarFormularioTransferencia() {
        return "transferencia"; // Asegúrate de que tienes una plantilla llamada "transferencia.html"
    }

    // Procesar la transferencia
    @PostMapping("/transferir")
    public String realizarTransferencia(@RequestParam String cuentaDestino, @RequestParam double monto, Model model) {
        // Aquí se podría usar la cuenta de origen del usuario autenticado (simplificado en este caso)
        String cuentaOrigen = "111111"; // Cambiar por la lógica de obtener la cuenta del usuario autenticado

        try {
            // Intentar realizar la transferencia
            boolean exito = servicioBanco.realizarTransferencia(cuentaOrigen, cuentaDestino, monto);
            if (exito) {
                model.addAttribute("mensaje", "Transferencia realizada exitosamente.");
            } else {
                model.addAttribute("mensaje", "Error: Saldo insuficiente o cuenta no encontrada.");
            }
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error: " + e.getMessage());
        }

        // Volver a la misma página para mostrar el resultado
        return "transferencia";
    }
}
