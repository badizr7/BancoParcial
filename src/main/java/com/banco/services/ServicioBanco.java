package com.banco.services;

import com.banco.model.Cuenta;
import com.banco.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioBanco {
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    public ServicioBanco() {
        usuarios.add(new Usuario("admin", "admin123", "ADMINISTRADOR"));
        usuarios.add(new Usuario("cliente", "cliente123", "USUARIO"));

        cuentas.add(new Cuenta("111111", 1500.00, "cliente"));
        cuentas.add(new Cuenta("222222", 3500.00, "admin"));
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentas;
    }

    public Cuenta obtenerCuentaPorTitular(String titular) {
        return cuentas.stream()
                       .filter(cuenta -> cuenta.getTitularCuenta().equals(titular))
                       .findFirst()
                       .orElse(null);
    }

    public void realizarTransferencia(String cuentaOrigen, String cuentaDestino, double monto) {
        Cuenta origen = cuentas.stream()
                .filter(cuenta -> cuenta.getNumeroCuenta().equals(cuentaOrigen))
                .findFirst().orElse(null);

        Cuenta destino = cuentas.stream()
                .filter(cuenta -> cuenta.getNumeroCuenta().equals(cuentaDestino))
                .findFirst().orElse(null);

        if (origen != null && destino != null && origen.getSaldoDisponible() >= monto) {
            origen.setSaldoDisponible(origen.getSaldoDisponible() - monto);
            destino.setSaldoDisponible(destino.getSaldoDisponible() + monto);
        }
    }
}
