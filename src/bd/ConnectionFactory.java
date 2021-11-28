package bd;/*
 * ConexaoPGSQL.java
 *
 * Classe usada para realizar a conexão com o banco de dados PostregreSQL
 *
 * Autor: Claudio A. Colares  05 de Maio de 2010 07:24
 *
 ********************************************************************************************/

import java.sql.*;
import javax.swing.JOptionPane;

/** Essa classe tem a finalidade de realizar uma conexao com uma base de dados PostegreSQL.
 * Nela existe dois metodos, o primeiro Conectar(), realiza a conexao com o banco de dados e o segundo, Desconectar(),
 * realiza a desconexao (Desconecta) o banco de dados.*/

public class ConnectionFactory {

  public static Connection getConnection() {

    /** Recebendo o endereco,usuario e senha do usuario e repassando para a variavel global */
    String endereco = "jdbc:postgresql://localhost:5432/ProjetoIntegrador";
    String usuario = "postgres";
    String senha = "251102";
    Connection con;

    try {
      /** Pasando o nome do Driver do PostgreSQL */
      Class.forName("org.postgresql.Driver");

      /** Obtendo a conexao com o banco de dados*/
      con = DriverManager.getConnection(endereco, usuario, senha);


      System.out.println("Conectado");
      return con;
      /** Retorna um erro caso nao encontre o driver, ou alguma informacao sobre o mesmo
       * esteja errada */
    } catch (ClassNotFoundException cnfe) {
      JOptionPane.showMessageDialog(null, "Erro ao conectar o driver");
      cnfe.printStackTrace();
      return null;
      /** Retorna um erro caso exista erro de query SQL */
    } catch (SQLException sqlex) {
      JOptionPane.showMessageDialog(null, "erro na query");
      sqlex.printStackTrace();
      return  null;
    }
  };

}
