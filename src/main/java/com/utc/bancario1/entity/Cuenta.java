package com.utc.bancario1.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private double saldo;
    private String tipo; // Ahorro, Corriente, etc.

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    public Cuenta() {}

    public Cuenta(String numero, double saldo, String tipo, Cliente cliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
