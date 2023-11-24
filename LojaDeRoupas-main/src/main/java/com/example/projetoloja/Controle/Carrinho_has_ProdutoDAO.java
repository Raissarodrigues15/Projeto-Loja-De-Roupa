package com.example.projetoloja.Controle;

import com.example.projetoloja.models.Carrinho_has_Produto;
import com.example.projetoloja.models.Produto;

import java.sql.SQLException;
import java.util.ArrayList;

public class Carrinho_has_ProdutoDAO extends ConnectionDAO{

    boolean sucesso = false; //Certifica se que  funcionou
    //INSERT
    public boolean insertCarrinho_has_Produto(int carrinho_idCarrinho, int produto_idProduto) {
        connectToDB();
        Carrinho_has_Produto carrinhoHasProduto = new Carrinho_has_Produto(carrinho_idCarrinho, produto_idProduto);
        // Adiciona o produto ao carrinho na tabela N-M
        String sql = "INSERT INTO Carrinho_has_Produto (Carrinho_idCarrinho, Produto_idProduto) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, carrinhoHasProduto.getCarrinho_idCarrinho());
            pst.setInt(2, carrinhoHasProduto.getProduto_idProduto());
            pst.execute();
            System.out.println("Produto adicionado ao Carrinho");
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: Produto não existe");
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

    public boolean deleteCarrinho_Has_Produto(int idCarrinho, int idProduto) {
        connectToDB();
        // Remove o produto do carrinho
        String sql = "DELETE FROM carrinho_has_produto where Carrinho_idCarrinho=? AND Produto_idProduto=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCarrinho);
            pst.setInt(2, idProduto);
            pst.execute();
            System.out.println("Produto removido do Carrinho");
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro: Produto não encontrado no carrinho");
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

    public boolean removerCarrinhoProdutoExcluido(int idProduto) {
        connectToDB();
        // Remove o produto de todos os carrinhos
        String sql = "DELETE FROM carrinho_has_produto where Produto_idProduto=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idProduto);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
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

    public double exibirCarrinho(int idUsuario) {
        connectToDB();
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        int idCarrinho = carrinhoDAO.selectCarrinhoID(idUsuario);
        double valorPedido = 0; // Armazena o valor total do carrinho
        // Mostra id, nome e valor dos produtos que estão no carrinho
        String sql = "SELECT p.idProduto, p.nome, p.valor FROM produto as p, carrinho_has_produto as chp, carrinho as c WHERE p.idProduto = chp.produto_idProduto AND chp.carrinho_idCarrinho = c.idCarrinho AND chp.carrinho_idCarrinho =?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,idCarrinho);
            rs = pst.executeQuery();
            System.out.println("--------------------------------");
            System.out.println("Carrinho: ");
            while (rs.next()) {
                Produto produtoAux = new Produto(rs.getInt("idProduto"),rs.getString("nome"),rs.getDouble("valor"));
                System.out.println("ID = " + produtoAux.getIdProduto());
                System.out.println("Nome = " + produtoAux.getNome());
                System.out.println("Valor = R$ " + String.format("%.2f",produtoAux.getValor()));
                System.out.println("--------------------------------");
                valorPedido += produtoAux.getValor(); //soma os preços dos itens
            }
            System.out.println("Valor da Compra : R$ " + String.format("%.2f",valorPedido));
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return valorPedido; // Retorna o valor total dos itens do carrinho
    }

    public ArrayList<Integer> selectProdutosCarrinho(int idCarrinho){
        connectToDB();
        ArrayList<Integer> produtos = new ArrayList<>(); // Armazena os IDs de todos os produtos que estão no carrinho
        String sql = "SELECT p.idProduto FROM produto as p, carrinho_has_produto as chp, carrinho as c WHERE p.idProduto = chp.produto_idProduto AND chp.carrinho_idCarrinho = c.idCarrinho AND chp.carrinho_idCarrinho =?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,idCarrinho);
            rs = pst.executeQuery();
            while (rs.next()) {
                produtos.add(rs.getInt("idProduto")); // Adiciona no arraylist
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return produtos; // Retorna os IDs de todos os produtos do carrinho
    }

    public boolean removerItensCarrinho(int idCarrinho) {
        connectToDB();
        // Remove todos os itens do carrinho
        String sql = "DELETE FROM carrinho_has_produto where Carrinho_idCarrinho=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCarrinho);
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
        return sucesso;
    }
}
