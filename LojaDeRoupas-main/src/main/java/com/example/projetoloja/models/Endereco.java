package com.example.projetoloja.models;

public class Endereco {
    private int idEndereco;
    private String rua;
    private String bairro;
    private int numero;
    private String cep;
    private int Usuario_idUsuario;

    public Endereco(String rua, String bairro, int numero, String cep, int Usuario_idUsuario) {
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    // Getters
    public int getIdEndereco() {
        return idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public int getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public int getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }
}
