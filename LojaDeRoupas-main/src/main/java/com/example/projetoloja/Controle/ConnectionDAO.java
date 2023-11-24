package com.example.projetoloja.Controle;
import java.sql.*;

public abstract class ConnectionDAO {
    Connection con; //conexão
    PreparedStatement pst; // declaração preparada - código em sql
    Statement st; //declaração - código em sql
    ResultSet rs; //resposta do banco
    String database = "LojaDeRoupas";//nome do BD
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/" + database + "?useroot=true&serverroot=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    public void connectToDB() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexao deu certo!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
}