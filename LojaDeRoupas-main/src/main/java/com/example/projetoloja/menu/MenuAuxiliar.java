package com.example.projetoloja.menu;

public class MenuAuxiliar { // Controle do logout e encerramento do programa
    private boolean flag; // Sinaliza o evento
    private boolean sucesso;// criado para retornar a variavel sucesso na main

    // Construtor
    public MenuAuxiliar(boolean flag, boolean sucesso) {
        this.flag = flag;
        this.sucesso = sucesso;
    }

    // Getters e setters
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }
}
