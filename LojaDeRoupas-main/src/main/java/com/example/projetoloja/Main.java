package com.example.projetoloja;

import com.example.projetoloja.Controle.UsuarioDAO;
import com.example.projetoloja.menu.MenuAuxiliar;
import com.example.projetoloja.menu.MenuLogin;
import com.example.projetoloja.menu.MenuLoja;
import com.example.projetoloja.models.Usuario;

import java.util.Scanner;

public class Main implements MenuLogin, MenuLoja { // Implementação de  Interfaces

    public static void main(String[] args) {

        // Variáveis auxiliares
        boolean flag = true;
        boolean sucesso = false;
        String email = null;
        String senha = null;
        Usuario aux = null;

        // Classe que conecta com BD
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Scanner in = new Scanner(System.in);
        while (flag) { // Loop para fazer login

            System.out.println("------------------------------------------------");
            System.out.println("Mara - Venha manter seu estilo com a gente! ");
            System.out.println("Digite 1 - Para fazer login");
            System.out.println("Digite 2 - Para se cadastrar");
            System.out.println("Digite 3 - Para encerrar");
            System.out.println("------------------------------------------------");

            int opLogin = in.nextInt(); // Opçoes do login
            while (opLogin != 1 && opLogin != 2 && opLogin != 3) { // Crítica de dados
                System.out.println("Opção Inválida. Digite 1 para fazer login ou 2 para se cadastrar ou 3 para encerrar: ");
                opLogin = in.nextInt();
            }
            if (opLogin == 1) { // Login
                try {
                    aux = MenuLogin.loginUsuario(); // Autenticação do usuário
                    sucesso = aux.sucesso;
                    System.out.println("Login realizado com sucesso!");
                    System.out.println("------------------------------------------------");
                    System.out.println("Seja bem-vindo, "+aux.getNome()+"!");
                }catch (NullPointerException e){
                }

            } else if (opLogin == 2) // Registro o usuario no sistema
                MenuLogin.registrarUsuario();
            else if (opLogin == 3) // Encerrar o programa
                flag = false;

            while (sucesso) { // Loop do menu da loja
                email = aux.getEmail();
                senha = aux.getSenha();
                MenuAuxiliar h = null;
                Usuario u = usuarioDAO.selectUsuarioLogin(email, senha); // Retorna o usuário após logar
                if (u.getCargo().equals("Cliente")) { // Menu do Cliente
                    h = MenuLoja.menuCliente(u);
                    flag = h.isFlag();
                    sucesso = h.isSucesso();
                }
                else if (u.getCargo().equals("Administrador")){ // Menu do ADM
                    h = MenuLoja.menuADM();
                    flag = h.isFlag();
                    sucesso = h.isSucesso();
                }
            }

        }

    }
}