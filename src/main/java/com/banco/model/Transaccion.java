package com.banco.model;

import java.time.LocalDateTime;

public class Transaccion {
    private String cuentaOrigen;
    private String cuentaDestino;
    private double monto; 
    private LocalDateTime fecha; 

    public Transaccion(String cuentaOrigen, String cuentaDestino, double monto){
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    public String getcuentaOrigen(){
        return cuentaOrigen;
    }

    public void setcuentaOrigen(){
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getcuentaDestino(){
        return cuentaDestino;
    }

    public void setcuentaDestino(){
        this.cuentaDestino = cuentaDestino;
    }

    public double getmonto(){
        return monto;
    }

    public void setmonto(){
        this.monto = monto;
    }

    public LocalDateTime getfecha(){
        return fecha;
    }

    public void setfecha(){
        this.fecha = fecha;
    }
}
