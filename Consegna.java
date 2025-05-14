package org.example;

import java.util.ArrayList;

public class Consegna {
    private Cliente cliente;
    private Restaurant ristorante;
    private boolean consegnata = false;
    private double totale;


    public Consegna(Cliente cliente, Restaurant ristorante, double totale) {
        this.cliente = cliente;
        this.ristorante = ristorante;
        this.totale = totale;
        this.consegnata = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Restaurant getRistorante() {
        return ristorante;
    }


    public void setConsegnata(boolean consegnata) {
        this.consegnata = consegnata;
    }

    public double getTotale() {
        return totale;
    }
}

