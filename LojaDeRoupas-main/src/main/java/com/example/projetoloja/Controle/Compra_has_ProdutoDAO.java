package com.example.projetoloja.Controle;

import com.example.projetoloja.models.Compra_has_Produto;

import java.sql.SQLException;

public class Compra_has_ProdutoDAO extends ConnectionDAO{

    boolean sucesso = false;
    //INSERT
    public boolean insertCompra_has_Produto(int Compra_idCompra, int Produto_idProduto) {
        connectToDB();
        Compra_has_Produto compraHasProduto = new Compra_has_Produto(Compra_idCompra, Produto_idProduto);
        // Insere os produtos referentes a cada compra na tabela N-M
        String sql = "INSERT INTO Compra_has_Produto (Compra_idCompra, Produto_idProduto) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, compraHasProduto.getCompra_idCompra());
            pst.setInt(2, compraHasProduto.getProduto_idProduto());
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
}
