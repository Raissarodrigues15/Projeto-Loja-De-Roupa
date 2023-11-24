package com.example.projetoloja.Controle;

import com.example.projetoloja.models.Carrinho;

import java.sql.SQLException;

public class CarrinhoDAO extends ConnectionDAO{

    boolean sucesso = false;
    //INSERT
    public boolean insertCarrinho(Carrinho carrinho) {
        connectToDB();
        // Cria um novo carrinho para quando um usuário é criado (Relacionamento 1-1)
        String sql = "INSERT INTO Carrinho (Usuario_idUsuario) values (?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, carrinho.getUsuario_idUsuario());
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

    public int selectCarrinhoID(int idUsuario) {
        connectToDB();
        // Pega o ID do Carrinho que pertence ao usuário do ID fornecido
        String sql = "SELECT idCarrinho FROM Carrinho, Usuario WHERE Carrinho.Usuario_idUsuario=?";
        int idCarrinho = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();
            if(rs.next()){
                idCarrinho = rs.getInt("idCarrinho");
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
        return idCarrinho;
    }
}

