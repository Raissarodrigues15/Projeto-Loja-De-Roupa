package com.example.projetoloja.models;

public class Compra {
    private int idCompra;
    private double valor;
    private String data;
    private int Usuario_idUsuario;

    // Construtores
    public Compra(double valor, String data, int usuario_idUsuario) {
        this.valor = valor;
        this.data = data;
        Usuario_idUsuario = usuario_idUsuario;
    }

    public Compra(int idCompra, double valor, String data, int usuario_idUsuario) {
        this.idCompra = idCompra;
        this.valor = valor;
        this.data = data;
        Usuario_idUsuario = usuario_idUsuario;
    }

    public Compra(int idCompra, double valor, String data) {
        this.idCompra = idCompra;
        this.valor = valor;
        this.data = data;
    }

    // Getters e setters

    public int getIdCompra() {
        return idCompra;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }
}
