package com.example.projetoloja.models;

public class Carrinho_has_Produto {

    private int Carrinho_idCarrinho;
    private int Produto_idProduto;

    // Construtor
    public Carrinho_has_Produto(int carrinho_idCarrinho, int produto_idProduto) {
        Carrinho_idCarrinho = carrinho_idCarrinho;
        Produto_idProduto = produto_idProduto;
    }

    // Getter
    public int getCarrinho_idCarrinho() {
        return Carrinho_idCarrinho;
    }

    public int getProduto_idProduto() {
        return Produto_idProduto;
    }
}
