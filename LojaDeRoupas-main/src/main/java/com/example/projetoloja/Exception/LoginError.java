package com.example.projetoloja.Exception;

public class LoginError extends Exception{ // Exceção para quando o login não funciona

    public LoginError() {
        System.out.println("E-mail ou senha inválidos");
    }
}
