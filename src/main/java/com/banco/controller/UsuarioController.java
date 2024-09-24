package com.banco.controller;

import com.banco.services.ServicioBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private ServicioBanco servicioBanco;

    // Maneja la solicitud GET para mostrar el formulario de transferencia
    @GetMapping("/transferir")
    public String mostrarFormularioTransferencia() {
        return "transferencia"; // Asegúrate de que tienes una plantilla "transferencia.html" en tu carpeta templates
    }

    // Maneja la solicitud POST para procesar la transferencia
    @PostMapping("/transferir")
    public String realizarTransferencia(@RequestParam String cuentaDestino, @RequestParam double monto, Model model) {
        // Aquí simulamos que el usuario autenticado es "cliente"
        String cuentaOrigen = "111111"; // Supón que la cuenta de origen pertenece al usuario autenticado

        try {
            // Realizamos la transferencia
            servicioBanco.realizarTransferencia(cuentaOrigen, cuentaDestino, monto);
            // Agregamos un mensaje de éxito
            model.addAttribute("mensaje", "Transferencia realizada exitosamente.");
        } catch (IllegalArgumentException e) {
            // Si hubo un error, lo mostramos
            model.addAttribute("mensaje", "Error: " + e.getMessage());
        }

        // Volvemos al formulario para que el usuario vea el resultado
        return "transferencia";
    }
}
