package com.example.projetoloja.models;

public class Produto {
    private int idProduto;
    private String nome;
    private double valor;
    private int qtd_disponivel;
    private String tecido;

    // Construtores
    public Produto(String nome, double valor, int qtd_disponivel, String tecido) {
        this.nome = nome;
        this.valor = valor;
        this.qtd_disponivel = qtd_disponivel;
        this.tecido = tecido;
    }

    public Produto(int idProduto, String nome, double valor, int qtd_disponivel, String tecido) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.valor = valor;
        this.qtd_disponivel = qtd_disponivel;
        this.tecido = tecido;
    }

    public Produto(int idProduto, String nome, double valor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.valor = valor;
    }

    // Getters e setters
    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public int getQtd_disponivel() {
        return qtd_disponivel;
    }

    public String gettecido() {
        return tecido;
    }
}
