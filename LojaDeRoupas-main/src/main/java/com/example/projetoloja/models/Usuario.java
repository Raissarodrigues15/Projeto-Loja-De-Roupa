package com.example.projetoloja.models;

public class Usuario {
    private int idUsuario;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private Carrinho carrinho;
    private Endereco endereco;
    private double saldo;
    public boolean sucesso;

    // Construtores
    public Usuario(String cpf, String nome, String email, String senha, String cargo, double saldo) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.saldo = saldo;
    }

    public Usuario(int idUsuario, String cpf, String nome, String email, String senha, String cargo, double saldo) {
        this.idUsuario = idUsuario;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.saldo = saldo;
    }

    public Usuario(String email, String senha, String cargo) {
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getSaldo() {
        return saldo;
    }

}
