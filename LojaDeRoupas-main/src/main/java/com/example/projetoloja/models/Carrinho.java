package com.example.projetoloja.models;

public class Carrinho {
    private int idCarrinho;
    private int Usuario_idUsuario;

    // Construtores
    public Carrinho(int usuario_idUsuario) {
        Usuario_idUsuario = usuario_idUsuario;
    }

    public Carrinho(int idCarrinho, int usuario_idUsuario) {
        this.idCarrinho = idCarrinho;
        Usuario_idUsuario = usuario_idUsuario;
    }

    // Getter
    public int getIdCarrinho() {
        return idCarrinho;
    }

    public int getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }
}
