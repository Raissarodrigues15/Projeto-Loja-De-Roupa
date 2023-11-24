package com.example.projetoloja.Exception;

public class PasswordError extends Exception{// Exceção para quando a senha estiver errado
    public PasswordError() {
        System.out.println("Senha Incorreta. Ação não  confirmada.");
    }
}
