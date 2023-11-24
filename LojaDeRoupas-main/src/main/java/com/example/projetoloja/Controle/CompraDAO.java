package com.example.projetoloja.Controle;
import com.example.projetoloja.models.Compra;
import java.sql.SQLException;

public class CompraDAO extends ConnectionDAO{

    boolean sucesso = false;
    //INSERT
    public boolean insertCompra(Compra compra) {
        connectToDB();
        // Insere o valor, data e FK do usuário que realizou a compra na tabela
        String sql = "INSERT INTO Compra (valor, data, Usuario_idUsuario) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setDouble(1, compra.getValor());
            pst.setString(2, compra.getData());
            pst.setInt(3, compra.getUsuario_idUsuario());
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

    public int selectUltimaCompraID(int idUsuario) {
        connectToDB();
        // Pega o ID da última compra realizada pelo usuário
        String sql = "SELECT idCompra FROM compra c, usuario u WHERE u.idUsuario = c.usuario_idusuario AND c.usuario_idusuario=? ORDER BY idCompra DESC LIMIT 1";
        int id = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();
            if(rs.next()){
                id = rs.getInt("idCompra");
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
        return id;
    }

    // Exibe id, data e valor de todas as compras realizadas pelo usuário
    public void verCompras(int idUsuario) {
        connectToDB();
        String sql = "SELECT c.idCompra, c.data, c.valor as valorCompra, p.nome, p.valor FROM compra c, usuario u, produto p, compra_has_produto chp WHERE u.idUsuario = c.usuario_idusuario AND chp.Compra_idCompra = c.idCompra AND chp.Produto_idProduto = p.idProduto AND u.idUsuario = ? ORDER BY c.idCompra";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();
            System.out.println("Lista de Compras: ");
            int idCompraAnterior = -1; // Variável para acompanhar o ID da compra anterior
            while (rs.next()) {
                int idCompra = rs.getInt("idCompra");
                if (idCompra != idCompraAnterior) {
                    Compra compraAux = new Compra(idCompra, rs.getDouble("valorCompra"), rs.getString("data"));
                    System.out.println("--------------------------------");
                    System.out.println("ID = " + compraAux.getIdCompra());
                    System.out.println("Data = " + compraAux.getData());
                    System.out.println("Valor Total = R$ " + String.format("%.2f",compraAux.getValor()));
                    System.out.println("Produtos: ");
                    idCompraAnterior = idCompra; // Atualiza o ID da compra anterior
                }
                String nomeProduto = rs.getString("nome");
                Double valorProduto = rs.getDouble("valor");
                System.out.println("Nome: " + nomeProduto);
                System.out.println("Valor: R$ " + String.format("%.2f",valorProduto));
            }
            System.out.println("--------------------------------");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

}