package com.example.projetoloja.Controle;

import com.example.projetoloja.models.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends ConnectionDAO{

    boolean sucesso = false;
    //INSERT
    public boolean insertUsuario(Usuario usuario) {
        connectToDB();
        // Adiciona um novo usuário na tabela
        String sql = "INSERT INTO Usuario (cpf,nome,email,senha,cargo,saldo) values(?,?,?,?,?,0)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getCpf());
            pst.setString(2, usuario.getNome());
            pst.setString(3, usuario.getEmail());
            pst.setString(4, usuario.getSenha());
            pst.setString(5, usuario.getCargo());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }
    //SELECT
    public ArrayList<Usuario> selectUsuario() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        connectToDB();
        // Exibe todos os usuários registrados
        String sql = "SELECT * FROM Usuario";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de usuarios: ");
            while (rs.next()) {
                Usuario usuarioAux = new Usuario(rs.getInt("idUsuario"),rs.getString("cpf"),rs.getString("nome"),rs.getString("email"),rs.getString("senha"),rs.getString("cargo"),rs.getDouble("saldo"));
                System.out.println("ID Usuário: " + usuarioAux.getIdUsuario());
                if(usuarioAux.getCargo().equals("Cliente")) {
                    System.out.println("CPF = " + usuarioAux.getCpf());
                    System.out.println("Nome = " + usuarioAux.getNome());
                }
                System.out.println("E-mail = " + usuarioAux.getEmail());
                System.out.println("Senha = " + usuarioAux.getSenha());
                System.out.println("Cargo = " + usuarioAux.getCargo());
                System.out.println("--------------------------------");
                usuarios.add(usuarioAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return usuarios;
    }

    public Usuario selectUsuarioLogin(String email, String senha) {
        connectToDB();
        // Faz a autenticação do login
        String sql = "SELECT * FROM Usuario WHERE email=? AND senha=?";
        Usuario usuario = null; // Inicia em  null

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            if(rs != null && rs.next()){ // Se houver um usuário correspondente ao email e senha
                usuario = new Usuario(rs.getInt("idUsuario"),rs.getString("cpf"),rs.getString("nome"),rs.getString("email"),rs.getString("senha"),rs.getString("cargo"),rs.getDouble("saldo"));
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return usuario;
    }

    public int selectUsuarioID(String email, String senha) {
        connectToDB();
        // Verifica o ID referente ao usuario do email e senha informados
        String sql = "SELECT idUsuario FROM Usuario WHERE email=? AND senha=?";
        int id = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            if(rs.next()){
                id = rs.getInt("idUsuario");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return id; // Retorna o id do usuário
    }
    public void depositar(int idUsuario, double valor) {
        connectToDB();
        // Aumenta o saldo de acordo com o valor digitado
        String sql = "UPDATE Usuario SET saldo = saldo + ? where idUsuario=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setDouble(1, valor);
            pst.setInt(2, idUsuario);
            pst.execute();
            System.out.println("Valor depositado com sucesso");
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
    }

    public void removerSaldo(int idUsuario, double valorCompra) {
        connectToDB();
        // Diminui o saldo de acordo com o valor da compra
        String sql = "UPDATE Usuario SET saldo = saldo - ? where idUsuario=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setDouble(1, valorCompra);
            pst.setInt(2, idUsuario);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
    }

}

