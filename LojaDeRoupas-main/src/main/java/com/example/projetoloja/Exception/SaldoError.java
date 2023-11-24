package com.example.projetoloja.Exception;

public class SaldoError extends Exception{ // Exceção para quando não há saldo para concluir uma compra

    public SaldoError() {
        System.out.println("A compra não foi concluída. Seu saldo é insuficiente.");
    }
}
