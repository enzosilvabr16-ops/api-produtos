package cotiinformatica.api_produtos.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    /*
        Método para retornar a conexão com o banco de dados
     */
    public static Connection getConnection() throws Exception {

        //Variáveis para conexão com o banco de dados
        var host = "jdbc:postgresql://localhost:5435/bd-produtos";
        var user = "postgres";
        var pass = "coti";

        //Abrindo e retornando a conexão com o banco de dados
        return DriverManager.getConnection(host, user, pass);
    }
}





