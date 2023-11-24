package com.example.projetoloja.Controle;
import com.example.projetoloja.models.Produto;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO extends ConnectionDAO{

    boolean sucesso = false; //Para saber se funcionou

    public boolean insertProduto(Produto produto) {
        connectToDB();
        // Informações do produto que vão para a tabela
        String sql = "INSERT INTO Produto (nome, valor, qtd_disponivel, tecido) values(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setDouble(2, produto.getValor());
            pst.setInt(3, produto.getQtd_disponivel());
            pst.setString(4, produto.gettecido());
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
    //UPDATE
    public boolean updateProduto(int id, Produto produto) {
        connectToDB();
        // Atualiza as informações de um unico produto
        String sql = "UPDATE Produto SET nome=?, valor=?, qtd_disponivel=?, Categoria=? where idProduto=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setDouble(2, produto.getValor());
            pst.setInt(3, produto.getQtd_disponivel());
            pst.setString(4, produto.gettecido());
            pst.setInt(5, id);
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
    //DELETE
    public boolean deleteProduto(int id) {
        connectToDB();
        // Remove um produto da tabela
        String sql = "DELETE FROM Produto where idProduto=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
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
    //SELECT
    public ArrayList<Produto> selectProduto() {
        ArrayList<Produto> produtos = new ArrayList<>();
        connectToDB();
        // Exibe todos os produtos
        String sql = "SELECT * FROM Produto";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de produtos: ");
            while (rs.next()) {
                System.out.println("Lista de produtos: ");
            Produto produtoAux = new Produto(rs.getInt("idProduto"), rs.getString("nome"), rs.getDouble("valor"), rs.getInt("qtd_disponivel"), rs.getString("tecido"));
            System.out.println("ID = " + produtoAux.getIdProduto());
            System.out.println("Nome = " + produtoAux.getNome());
            System.out.println("Valor = R$ " + String.format("%.2f", produtoAux.getValor()));
            System.out.println("Qtd disponível = " + produtoAux.getQtd_disponivel());
            System.out.println("Categoria = " + produtoAux.gettecido());
            System.out.println("--------------------------------");
            produtos.add(produtoAux);
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
        return produtos;
    }

    // Mostra todas as informações referentes a um único produto
    public Produto selectProdutoEspecifico(int idProduto) {
        Produto produtoAux = null;
        connectToDB();
        String sql = "SELECT * FROM Produto WHERE idProduto = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,idProduto);
            rs = pst.executeQuery();
            System.out.println("Informações do Produto " + idProduto + " :");
            if (rs.next()) {
                produtoAux = new Produto(rs.getInt("idProduto"),rs.getString("nome"),rs.getDouble("valor"),rs.getInt("qtd_disponivel"),rs.getString("tecido"));
                System.out.println("Nome = " + produtoAux.getNome());
                System.out.println("Valor = R$ " + String.format("%.2f",produtoAux.getValor()));
                System.out.println("Qtd disponível = " + produtoAux.getQtd_disponivel());
                System.out.println("Categoria = " + produtoAux.gettecido());
                System.out.println("--------------------------------");
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
        return produtoAux;
    }

    // Diminui 1 unidade do estoque do produto ao realizar uma compra
    public boolean diminuirEstoque(int idProduto) {
        connectToDB();
        String sql = "UPDATE Produto SET qtd_disponivel= qtd_disponivel - 1 WHERE idProduto=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idProduto);
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