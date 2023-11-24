package com.example.projetoloja.Exception;

public class RegistroError extends Exception{ // Exceção para quando o registro não ocorrer bem

    public RegistroError() {
        System.out.println("Ouve uma falha ao registrar. Tente novamente.");
    }
}
