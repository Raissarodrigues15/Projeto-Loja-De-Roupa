package com.example.projetoloja.models;

public class Compra_has_Produto {

    private int Compra_idCompra;
    private int Produto_idProduto;

    // Construtor
    public Compra_has_Produto(int compra_idCompra, int produto_idProduto) {
        Compra_idCompra = compra_idCompra;
        Produto_idProduto = produto_idProduto;
    }

    // Getters
    public int getCompra_idCompra() {
        return Compra_idCompra;
    }

    public int getProduto_idProduto() {
        return Produto_idProduto;
    }

}
